package com.rigatron.rigs4j.web.models;

import com.rigatron.rigs4j.BL.entities.UserRole;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

    public int id;
    public String username;
    public String password;
    public Set<UserRole> roles = new HashSet<>();
    public Date cookieExpiry;
}
