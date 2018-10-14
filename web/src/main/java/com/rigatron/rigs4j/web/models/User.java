package com.rigatron.rigs4j.web.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

    private int id;
    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
    private Date createDate;
    private Date lastModifiedDate;

    public int getId() { return this.id; }

    public void setId(int value) { this.id = value; }

    public String getUsername() { return this.username; }

    public void setUsername(String value) { this.username = value; }

    public String getPassword() { return this.password; }

    public void setPassword(String value) { this.password = value; }

    public Set<UserRole> getRoles() { return this.roles; }

    public void setRoles(Set<UserRole> value) { this.roles = value; }

    public Date getCreateDate() { return this.createDate; }

    public void setCreateDate(Date value) { this.createDate = value; }

    public Date getLastModifiedDate() { return this.lastModifiedDate; }

    public void setLastModifiedDate(Date value) { this.lastModifiedDate = value; }
}
