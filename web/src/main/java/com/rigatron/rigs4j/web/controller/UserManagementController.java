package com.rigatron.rigs4j.web.controller;

import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import com.rigatron.rigs4j.web.models.User;
import com.rigatron.rigs4j.web.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/usermanagement")
public class UserManagementController {

    @Autowired
    private IUserService userService;

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView userList() {

        List<User> users = MapUsers(userService.getAllUsers());

        return new ModelAndView("userList", "users", users);
    }

    private List<User> MapUsers(List<com.rigatron.rigs4j.BL.entities.User> users) {

        List<User> mapped = new ArrayList<>();

        for(com.rigatron.rigs4j.BL.entities.User u : users) {
            User user = new User();

            user.setId(u.getId());
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setRoles(MapRoles(u.getRoles()));
            user.setCreateDate(u.getCreateDate());
            user.setLastModifiedDate(u.getLastModifiedDate());
            user.setIsEnabled(u.getIsEnabled());

            mapped.add(user);
        }

        return mapped;
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

