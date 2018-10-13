package com.rigatron.rigs4j.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) boolean error) {

        String errorMessage = null;

        if(error == true) {
            errorMessage = "Username or Password is incorrect !!";
        }

        return new ModelAndView("login", "errorMessage", errorMessage);
    }


    @RequestMapping(value="/register")
    public ModelAndView Register() {
        return new ModelAndView("register");
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ModelAndView("redirect:/home?logout=true");
    }

    @RequestMapping(value="/adduser", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("username") String username, @RequestParam("password") String password) {

        try {
            userService.createUser(username, password);

            ModelAndView model = new ModelAndView("redirect:/home?registered=true");

            return model;
        }
        catch (Exception e) {

            ModelAndView model = new ModelAndView("register", "errorMessage", e.getLocalizedMessage());

            return model;
        }
    }
}