package com.rigatron.rigs4j.BL.services;

import java.util.ArrayList;
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

    private IUserDAO userDAO;
    private IUserRoleDAO roleDAO;

    public void setUserDAO(IUserDAO dao) {
        this.userDAO = dao;
    }

    public void setUserRoleDAO(IUserRoleDAO dao) {
        this.roleDAO = dao;
    }

    @Override
    @Transactional(readOnly=true)
    public User login(String username, String password) throws UsernameNotFoundException, PasswordNotMatched {

        User user = this.userDAO.getUserByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        if (!BCrypt.checkpw(password, user.password)) {
            throw new PasswordNotMatched();
        }

        return user;
    }

    @Override
    @Transactional
    public void createUser(User user) {

        user.createDate = new Date();
        user.lastModifiedDate = new Date();

        UserRole role = new UserRole();

        role.id = Roles.REGULARUSER.getValue();
        role.role = Roles.REGULARUSER.toString();

        user.roles.clear();
        user.roles.add(role);

        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());

        this.userDAO.createUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        this.userDAO.deleteUserById(id);
    }

}
