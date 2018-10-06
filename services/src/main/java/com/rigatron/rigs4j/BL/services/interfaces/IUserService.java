package com.rigatron.rigs4j.BL.services.interfaces;

import java.util.List;
import com.rigatron.rigs4j.BL.entities.User;

public interface IUserService {

    void createUser(User p);
    void updateUser(User p);
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUserById(int id);

}