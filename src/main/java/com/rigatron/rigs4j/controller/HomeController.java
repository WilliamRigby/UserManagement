package com.rigatron.rigs4j.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.print.URIException;
import javax.servlet.http.HttpServletResponse;

import com.rigatron.rigs4j.dao.UserDAO;
import com.rigatron.rigs4j.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; // this is your lifesaver right here
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private UserDAO userDAO;


	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException {

		List<User> users = userDAO.selectTop100();

		ModelAndView model = new ModelAndView("home");
		model.addObject("users", users);

		return model;
	}


	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public @ResponseBody String register(@RequestBody User user, HttpServletRequest request) throws JsonMappingException, JsonParseException, IOException {

		// now simply convert your JSON string into your UserProfile POJO
		// using Jackson's ObjectMapper.readValue() method, whose first
		// parameter your JSON parameter as String, and the second
		// parameter is the POJO class.

		//User profile = new ObjectMapper().readValue(data, User.class);


		userDAO.insert(user);

		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();
		ObjectNode objectNode1 = mapper.createObjectNode();
		objectNode1.put("response", "success");
		arrayNode.add(objectNode1);

		return arrayNode.toString();

		// rest of your code goes here.
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody User user, HttpServletRequest request) throws JsonMappingException, JsonParseException, IOException
	{
		User returned = userDAO.login(user);

		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();
		ObjectNode objectNode1 = mapper.createObjectNode();


		if(returned.getPassword() != null && returned.getPassword().equals(user.getPassword())) {
			objectNode1.put("response", "match");
			arrayNode.add(objectNode1);
		}
		else {
			objectNode1.put("response", "error");
			arrayNode.add(objectNode1);
		}

		return arrayNode.toString();

	}


		@RequestMapping(value="/test", method = RequestMethod.GET)
	public ModelAndView test2(HttpServletResponse response) throws JsonMappingException, JsonParseException, IOException {

		// now simply convert your JSON string into your UserProfile POJO
		// using Jackson's ObjectMapper.readValue() method, whose first
		// parameter your JSON parameter as String, and the second
		// parameter is the POJO class.

		//User profile = new ObjectMapper().readValue(data, User.class);

		ModelAndView model = new ModelAndView("interests");

		return model;
		// rest of your code goes here.
	}








}
