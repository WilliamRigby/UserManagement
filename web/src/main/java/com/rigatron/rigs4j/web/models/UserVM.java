package com.rigatron.rigs4j.web.models;

import com.rigatron.rigs4j.BL.entities.enums.Roles;

import java.util.*;

public class UserVM {

    private int id;
    private String username;
    private String password;
    private Set<UserRoleVM> roles = new HashSet<>();
    private Date createDate;
    private Date lastModifiedDate;
    private boolean isEnabled;

    public int getId() { return this.id; }

    public void setId(int value) { this.id = value; }

    public String getUsername() { return this.username; }

    public void setUsername(String value) { this.username = value; }

    public String getPassword() { return this.password; }

    public void setPassword(String value) { this.password = value; }

    public Set<UserRoleVM> getRoles() { return this.roles; }

    public void setRoles(Set<UserRoleVM> value) {
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

        List<UserRoleVM> roles = new ArrayList<>(this.roles);

        for(int i = 0; i < roles.size(); i++) {
            s += roles.get(i).getRole();

            if(i < roles.size() - 1) {
                s += "<br/>";
            }
        }

        return s;
    }

    public boolean getIsAdmin() {
        List<UserRoleVM> roles = new ArrayList<>(this.roles);

        for(UserRoleVM role : roles) {
            if(role.getRole().equals(Roles.ADMIN.toString())) {
                return true;
            }
        }

        return false;
    }
}
