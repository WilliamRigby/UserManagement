package com.rigatron.rigs4j.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rigatron.rigs4j.web.Utilities.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.models.User;

import java.io.IOException;

@Controller
public class HomeController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/")
	public ModelAndView Index() {
		return new ModelAndView("home");
	}

	@RequestMapping(value="/register")
	public ModelAndView Register() {
		return new ModelAndView("register");
	}

	/*
	@RequestMapping(value="/login")
	public ModelAndView Login(@RequestParam(value = "error",required = false) String error,
							  @RequestParam(value = "logout",	required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from JournalDEV successfully.");
		}

		model.setViewName("login");

		return model;
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

	*/

	@RequestMapping(value="/restricted")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("loggedIn");
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
