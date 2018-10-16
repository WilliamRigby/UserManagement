package com.rigatron.rigs4j.web.controller;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.models.User;
import com.rigatron.rigs4j.web.models.UserRole;
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

import java.util.HashSet;
import java.util.Set;
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
                User duplicate = MapUser(userService.getUserByName(username));
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

    private User MapUser(com.rigatron.rigs4j.BL.entities.User u) {

        User user = new User();

        user.setId(u.getId());
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setRoles(MapRoles(u.getRoles()));
        user.setCreateDate(u.getCreateDate());
        user.setLastModifiedDate(u.getLastModifiedDate());
        user.setIsEnabled(u.getIsEnabled());

        return user;
    }

    private Set<UserRole> MapRoles(Set<com.rigatron.rigs4j.BL.entities.UserRole> roles) {

        Set<UserRole> mapped = new HashSet<>();

        for(com.rigatron.rigs4j.BL.entities.UserRole r : roles) {
            UserRole role = new UserRole();

            role.setId(r.getId());
            role.setRole(r.getRole());

            mapped.add(role);
        }

        return mapped;
    }
}