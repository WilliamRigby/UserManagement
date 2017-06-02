package com.rigatron.rigs4j.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by willi on 6/1/2017.
 */
@Controller
public class InterestsController {

    @RequestMapping(value="/interests")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        return new ModelAndView("interests");
    }
}