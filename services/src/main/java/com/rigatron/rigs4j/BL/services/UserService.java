package com.rigatron.rigs4j.BL.services;



import java.util.List;

import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserRoleDAO;
import com.rigatron.rigs4j.BL.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void createUser(User user) {
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
