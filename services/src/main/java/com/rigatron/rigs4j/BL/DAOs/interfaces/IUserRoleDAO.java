package com.rigatron.rigs4j.BL.DAOs.interfaces;

import com.rigatron.rigs4j.BL.entities.UserRole;

import java.util.List;

public interface IUserRoleDAO {

    void createUserRole(UserRole u);
    void updateUserRole(UserRole u);
    List<UserRole> getAllUserRoles();
    UserRole getUserRoleById(int id);
    void deleteUserRoleById(int id);
}
