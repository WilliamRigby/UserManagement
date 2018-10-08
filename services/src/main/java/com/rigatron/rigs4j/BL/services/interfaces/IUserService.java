package com.rigatron.rigs4j.BL.services.interfaces;

import java.util.List;
import com.rigatron.rigs4j.BL.entities.User;
import com.rigatron.rigs4j.BL.entities.exceptions.PasswordNotMatched;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    User login(String username, String password) throws UsernameNotFoundException, PasswordNotMatched;
    void createUser(String username, String password);
    void updateUser(User p);
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUserById(int id);

}