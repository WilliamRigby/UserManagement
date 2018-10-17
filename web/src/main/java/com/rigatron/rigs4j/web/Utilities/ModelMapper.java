package com.rigatron.rigs4j.web.Utilities;

import com.rigatron.rigs4j.web.models.UserRoleVM;
import com.rigatron.rigs4j.web.models.UserVM;

import java.util.HashSet;
import java.util.Set;

public class ModelMapper {

    public static UserVM MapUser(com.rigatron.rigs4j.BL.entities.User u) {

        UserVM user = new UserVM();

        user.setId(u.getId());
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setRoles(MapRoles(u.getRoles()));
        user.setCreateDate(u.getCreateDate());
        user.setLastModifiedDate(u.getLastModifiedDate());
        user.setIsEnabled(u.getIsEnabled());

        return user;
    }

    public static Set<UserRoleVM> MapRoles(Set<com.rigatron.rigs4j.BL.entities.UserRole> roles) {

        Set<UserRoleVM> mapped = new HashSet<>();

        for(com.rigatron.rigs4j.BL.entities.UserRole r : roles) {
            UserRoleVM role = new UserRoleVM();

            role.setId(r.getId());
            role.setRole(r.getRole());

            mapped.add(role);
        }

        return mapped;
    }
}
