package com.rigatron.rigs4j.web.Utilities;

import com.rigatron.rigs4j.BL.services.UserService;
import com.rigatron.rigs4j.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LayoutInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    private static final String NO_LAYOUT = "noLayout:";

    private static final String LAYOUT = "_layout";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);

        if(modelAndView == null) {
            return;
        }

        Cookie sessionCookie = StateManager.getCookie(request);

        if(sessionCookie != null) {

            User user = StateManager.getUserFromSession(request);

            if(user != null && user.cookieExpiry.after(new Date())) {
                modelAndView.addObject("user", user);
            }
            else if(user != null && !user.cookieExpiry.after(new Date())) {
                StateManager.deleteUserFromSession(request);
            }
        }

        String originalView = modelAndView.getViewName();

        if (originalView != null && !originalView.startsWith("redirect:")) {
            includeLayout(modelAndView, originalView);
        }
    }

    private void includeLayout(ModelAndView modelAndView, String originalView) {

        boolean noLayout = originalView.startsWith(NO_LAYOUT);

        String realViewName = noLayout ? originalView.substring(NO_LAYOUT.length()) : originalView;

        if (noLayout) {
            modelAndView.setViewName(realViewName);
        }
        else {
            modelAndView.addObject("view", realViewName);
            modelAndView.setViewName(LAYOUT);
        }
    }

    private String readCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for (Cookie c : cookies) {
                if(c.getName() == key) {
                    return c.getValue();
                }
            }
        }

        return null;
    }
}