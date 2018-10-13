package com.rigatron.rigs4j.web.Utilities;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String LAYOUT = "_layout";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);

        if(modelAndView == null) {
            return;
        }

        if (modelAndView.getViewName() != null && !modelAndView.getViewName().startsWith("redirect:")) {
            modelAndView.addObject("view", modelAndView.getViewName());
            modelAndView.setViewName(LAYOUT);
        }
    }
}