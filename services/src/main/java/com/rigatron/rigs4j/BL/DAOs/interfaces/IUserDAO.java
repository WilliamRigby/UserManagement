package com.rigatron.rigs4j.BL.DAOs.interfaces;

import java.util.List;
import com.rigatron.rigs4j.BL.entities.User;

public interface IUserDAO {

    void createUser(User u);
    void updateUser(User u);
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUserById(int id);
}
