package com.rigatron.rigs4j.web.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class HomeController {

	@RequestMapping(value= {"/", "/home"})
	public ModelAndView Index(@RequestParam(value = "registered", required = false) String registered,
							  @RequestParam(value = "loggedin", required = false) String loggedin,
							  @RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView("home");

		if(registered != null) {
			model.addObject("message", "Thank you for registering.  Please log in now.");
		}
		else if(loggedin != null) {
			model.addObject("message", "You have successfully logged in!");
		}
		else if(logout != null) {
			model.addObject("message", "You have been successfully logged out!");
		}

		return model;
	}

	@RequestMapping(value="/restricted")
	public ModelAndView UserRestricted(HttpServletResponse response) throws IOException {
		return new ModelAndView("restricted");
	}

}
