package com.rigatron.rigs4j.web.controller;

import java.util.Date;
import javax.servlet.http.HttpServletResponse;

import com.rigatron.rigs4j.BL.entities.UserRole;
import com.rigatron.rigs4j.web.models.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.servlet.http.HttpServletRequest;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.models.User;

@Controller
public class HomeController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/")
	public ModelAndView Index(HttpServletResponse response) {

		ModelAndView model = new ModelAndView("home");

		return model;
	}


	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public @ResponseBody String Register(@RequestBody User user, HttpServletRequest request) {

		com.rigatron.rigs4j.BL.entities.User BLUser = MapBLUser(user);

		BLUser.createDate = new Date();
		BLUser.lastModifiedDate = new Date();

		UserRole BLUserRole = new UserRole();

		BLUserRole.id = Roles.REGULARUSER.getValue();
		BLUserRole.role = Roles.REGULARUSER.toString();

		BLUser.roles.add(BLUserRole);

		userService.createUser(BLUser);

		return "success";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody String Login(@RequestBody User user, HttpServletRequest request) {

		User returned = MapUser(userService.getUserById(user.id));

		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();
		ObjectNode objectNode1 = mapper.createObjectNode();


		if(returned != null && returned.getPassword() != null && returned.getPassword().equals(user.getPassword()))
		{
			objectNode1.put("response", "match");
			arrayNode.add(objectNode1);
		}
		else if( returned != null && returned.getPassword() != null && !returned.getPassword().equals(user.getPassword()))
		{
			objectNode1.put("response", "incorrect_pass");
			arrayNode.add(objectNode1);
		}
		else if(returned == null || returned.getPassword() == null)
		{
			objectNode1.put("response", "null");
			arrayNode.add(objectNode1);
		}

		return arrayNode.toString();
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
