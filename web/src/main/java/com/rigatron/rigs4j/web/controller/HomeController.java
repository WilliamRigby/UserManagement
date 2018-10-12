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

	@RequestMapping(value="/")
	public ModelAndView Index(@RequestParam(value = "registered", required = false) boolean registered,
							  @RequestParam(value = "loggedin", required = false) boolean loggedin) {

		ModelAndView model = new ModelAndView("home");
		String message = null;

		if(registered == true) {
			message = "Thank you for registering.  Please log in now.";
		}
		else if(loggedin = true) {
			message = "You have successfully logged in!";
		}

		model.addObject("message", message);

		return model;
	}

	@RequestMapping(value="/register")
	public ModelAndView Register() {
		return new ModelAndView("register");
	}

	@RequestMapping(value="/restricted")
	public ModelAndView UserRestricted(HttpServletResponse response) throws IOException {
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
