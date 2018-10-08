package com.rigatron.rigs4j.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.models.User;

@Controller
public class HomeController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/")
	public ModelAndView Index() {

		ModelAndView model = new ModelAndView("home");

		return model;
	}

	@RequestMapping(value="/adduser", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public ModelAndView Register(@ModelAttribute String username, @ModelAttribute String password) {

		try {
            userService.createUser(username, password);
            return new ModelAndView("home", "message", "Thank you for registering.  Please log in now.");
        }
        catch (Exception e) {
            return new ModelAndView("home", "message", e.getLocalizedMessage());
        }
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView Login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

	    try {
            User result = MapUser(userService.login(user.username, user.password));

			Cookie cookie = new Cookie("userId", ((Integer)user.id).toString());

			cookie.setMaxAge(60);

			response.addCookie(cookie);

            return new ModelAndView("home", "user", result);
        }
        catch(Exception e) {
	        return new ModelAndView("home");
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
