package com.rigatron.rigs4j.BL.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserRoleDAO;
import com.rigatron.rigs4j.BL.entities.User;
import com.rigatron.rigs4j.BL.entities.UserRole;
import com.rigatron.rigs4j.BL.entities.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.*;
import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserDAO;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    private IUserDAO userDAO;

    public void setUserDAO(IUserDAO dao) {
        this.userDAO = dao;
    }

    @Override
    public void createUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setCreateDate(new Date());
        user.setLastModifiedDate(new Date());
        user.setIsEnabled(true);

        UserRole role = new UserRole();

        role.setId(Roles.REGULARUSER.getValue());
        role.setRole(Roles.REGULARUSER.toString());

        user.getRoles().add(role);

        this.userDAO.createUser(user);
    }

    @Override
    public void updateUser(int userId, boolean isEnabled, boolean isAdmin) {

        User user = this.userDAO.getUserById(userId);

        user.setIsEnabled(isEnabled);

        toggleAdmin(user, isAdmin);

        this.userDAO.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String username) { return this.userDAO.getUserByName(username); }

    @Override
    public void deleteUserById(int id) {
        this.userDAO.deleteUserById(id);
    }

    private void toggleAdmin(User user, boolean newIsAdmin) {

        boolean alreadyAdmin = false;

        for(UserRole role : user.getRoles()) {
            if(role.getRole().equals(Roles.ADMIN.toString())) {
                alreadyAdmin = true;
            }
        }

        if(!alreadyAdmin && newIsAdmin) { // add admin role

            UserRole role = new UserRole();

            role.setId(user.getId());
            role.setRole(Roles.ADMIN.toString());

            Set<UserRole> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
        }
        else if(alreadyAdmin && !newIsAdmin) { // remove admin role

            Set<UserRole> roles = user.getRoles();
            Set<UserRole> newRoles = new HashSet<>();

            for(UserRole role : roles) {
                if(!role.getRole().equals(Roles.ADMIN.toString())) {
                    newRoles.add(role);
                }
            }

            user.setRoles(newRoles);
        }
    }
}
