package com.rigatron.rigs4j.web.controller;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.Utilities.ModelMapper;
import com.rigatron.rigs4j.web.models.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

@Controller
public class AccountController {

    @Autowired
    private IUserService userService;

    private final String usernameRegex = "^[A-Za-z0-9]{3,}$";
    private final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @PermitAll
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) {

        ModelAndView model = new ModelAndView("login");

        if(error != null) {
            model.addObject("errorMessage", "Username or password is incorrect");
        }

        return model;
    }

    @PermitAll
    @RequestMapping(value="/register")
    public ModelAndView register(@RequestParam(value = "invalidUsername", required = false) String invalidUsername,
                                 @RequestParam(value = "invalidPassword", required = false) String invalidPassword,
                                 @RequestParam(value = "duplicateUsername", required = false) String duplicateUsername,
                                 @RequestParam(value = "error", required = false) String error) {

        ModelAndView model = new ModelAndView("register");

        if(invalidUsername != null) {
            model.addObject("invalidUsername", "Please check the username requirements");
        }

        if(invalidPassword != null) {
            model.addObject("invalidPassword", "Please check the password requirements");
        }

        if(duplicateUsername != null) {
            model.addObject("duplicateUsername", "That username is already taken");
        }

        if(error != null) {
            model.addObject("errorMessage", "There has been an error");
        }

        return model;
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ModelAndView("redirect:/home?logout=true");
    }

    @PermitAll
    @RequestMapping(value="/adduser", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("username") String username, @RequestParam("password") String password) {

        try {

            boolean isValidUsername = Pattern.matches(usernameRegex, username);
            boolean isValidPassword = Pattern.matches(passwordRegex, password);

            if(!isValidUsername && !isValidPassword) {
                return new ModelAndView("redirect:/register?invalidUsername=true&invalidPassword=true");
            }
            else if(!isValidUsername) {
                return new ModelAndView("redirect:/register?invalidUsername=true");
            }
            else if(!isValidPassword) {
                return new ModelAndView("redirect:/register?invalidPassword=true");
            }

            try {
                UserVM duplicate = ModelMapper.MapUser(userService.getUserByName(username));
                return new ModelAndView("redirect:/register?duplicateUsername=true");
            }
            catch(EmptyResultDataAccessException e) { }

            userService.createUser(username, password);

            return new ModelAndView("redirect:/home?registered=true");
        }
        catch (Exception e) {

            return new ModelAndView("redirect:/register?error=true");
        }
    }
}