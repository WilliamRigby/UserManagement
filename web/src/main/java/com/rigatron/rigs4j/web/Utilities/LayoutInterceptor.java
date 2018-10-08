package com.rigatron.rigs4j.web.Utilities;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String NO_LAYOUT = "noLayout:";

    private static final String LAYOUT = "_layout";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);

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
}