package com.rigatron.rigs4j.web.models;

import java.util.*;

public class User {

    private int id;
    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
    private Date createDate;
    private Date lastModifiedDate;
    private boolean isEnabled;

    public int getId() { return this.id; }

    public void setId(int value) { this.id = value; }

    public String getUsername() { return this.username; }

    public void setUsername(String value) { this.username = value; }

    public String getPassword() { return this.password; }

    public void setPassword(String value) { this.password = value; }

    public Set<UserRole> getRoles() { return this.roles; }

    public void setRoles(Set<UserRole> value) {
        this.roles = value;
    }

    public Date getCreateDate() { return this.createDate; }

    public void setCreateDate(Date value) { this.createDate = value; }

    public Date getLastModifiedDate() { return this.lastModifiedDate; }

    public void setLastModifiedDate(Date value) { this.lastModifiedDate = value; }

    public boolean getIsEnabled() { return this.isEnabled; }

    public void setIsEnabled(boolean value) { this.isEnabled = value; }

    public String getRoleString() {
        String s = "";

        List<UserRole> roles = new ArrayList<>(this.roles);

        for(int i = 0; i < roles.size(); i++) {
            s += roles.get(i).getRole();

            if(i < roles.size() - 1) {
                s += "<br/>";
            }
        }

        return s;
    }
}
