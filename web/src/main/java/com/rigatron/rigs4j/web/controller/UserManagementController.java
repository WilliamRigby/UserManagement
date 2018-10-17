package com.rigatron.rigs4j.web.controller;

import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.Utilities.ModelMapper;
import com.rigatron.rigs4j.web.models.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usermanagement")
public class UserManagementController {

    @Autowired
    private IUserService userService;

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView userList() {

        List<UserVM> users = new ArrayList<>();

        for(com.rigatron.rigs4j.BL.entities.User u : userService.getAllUsers()) {
            UserVM user = ModelMapper.MapUser(u);
            users.add(user);
        }

        return new ModelAndView("userList", "users", users);
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public ModelAndView userDetails(@RequestParam(value = "userId", required = true) int userId) {

        UserVM user = ModelMapper.MapUser(userService.getUserById(userId));

        return new ModelAndView("userDetails", "user", user);
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam(value = "userId", required = true) int userId,
                                   @RequestParam(value = "isEnabled", required = true) boolean isEnabled,
                                   @RequestParam(value = "isAdmin", required = true) boolean isAdmin) {

        this.userService.updateUser(userId, isEnabled, isAdmin);

        return new ModelAndView("redirect:/usermanagement/list");
    }
}

