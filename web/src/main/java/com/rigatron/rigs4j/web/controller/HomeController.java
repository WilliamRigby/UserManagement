package com.rigatron.rigs4j.web.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class HomeController {

	@RequestMapping(value= {"/", "/home"})
	public ModelAndView Index(@RequestParam(value = "registered", required = false) boolean registered,
							  @RequestParam(value = "loggedin", required = false) boolean loggedin,
							  @RequestParam(value = "logout", required = false) boolean logout) {

		ModelAndView model = new ModelAndView("home");
		String message = null;

		if(registered == true) {
			message = "Thank you for registering.  Please log in now.";
		}
		else if(loggedin == true) {
			message = "You have successfully logged in!";
		}
		else if(logout == true) {
			message = "You have been successfully logged out!";
		}

		model.addObject("message", message);

		return model;
	}

	@RequestMapping(value="/restricted")
	public ModelAndView UserRestricted(HttpServletResponse response) throws IOException {
		return new ModelAndView("restricted");
	}

}
