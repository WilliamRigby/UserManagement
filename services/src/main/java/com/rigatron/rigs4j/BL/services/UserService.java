package com.rigatron.rigs4j.BL.services;

import java.util.Date;
import java.util.List;

import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserRoleDAO;
import com.rigatron.rigs4j.BL.entities.User;
import com.rigatron.rigs4j.BL.entities.UserRole;
import com.rigatron.rigs4j.BL.entities.enums.Roles;
import com.rigatron.rigs4j.BL.entities.exceptions.PasswordNotMatched;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.*;
import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserDAO;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    private IUserDAO userDAO;
    private IUserRoleDAO roleDAO;

    public void setUserDAO(IUserDAO dao) {
        this.userDAO = dao;
    }

    public void setUserRoleDAO(IUserRoleDAO dao) {
        this.roleDAO = dao;
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
    public void updateUser(User user) {
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
    public void deleteUserById(int id) {
        this.userDAO.deleteUserById(id);
    }

}
