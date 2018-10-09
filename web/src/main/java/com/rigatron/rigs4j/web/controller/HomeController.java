package com.rigatron.rigs4j.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rigatron.rigs4j.web.Utilities.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.models.User;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class HomeController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/")
	public ModelAndView Index() {

		ModelAndView model = new ModelAndView("home");

		return model;
	}

	@RequestMapping(value="/register")
	public ModelAndView Register() {

		return new ModelAndView("register");
	}

	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public ModelAndView AddUser(@RequestParam("username") String username, @RequestParam("password") String password) {

		try {
            userService.createUser(username, password);
            return new ModelAndView("home", "message", "Thank you for registering.  Please log in now.");
        }
        catch (Exception e) {
            return new ModelAndView("home", "message", e.getLocalizedMessage());
        }
	}

    @RequestMapping(value="/login")
    public ModelAndView Login() {

        return new ModelAndView("login");
    }

	@RequestMapping(value="/loginrequest", method = RequestMethod.POST)
	public ModelAndView LoginRequest(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {

	    try {
            User result = MapUser(userService.login(username, password));

            String sessionId = StateManager.updateSessionCookie(request, response);
            StateManager.updateSession(request, result, sessionId);

            return new ModelAndView("home", "user", result);
        }
        catch(Exception e) {
	        return new ModelAndView("home", "message", e.getMessage());
        }
	}

	private User MapUser(com.rigatron.rigs4j.BL.entities.User u) {

	    User user = new User();

	    user.id = u.id;
	    user.username = u.username;
	    user.password = u.password;

	    return user;
	}

    private com.rigatron.rigs4j.BL.entities.User MapBLUser(User u) {

        com.rigatron.rigs4j.BL.entities.User user = new com.rigatron.rigs4j.BL.entities.User();

        user.id = u.id;
        user.username = u.username;
        user.password = u.password;

        return user;
    }


}
