package com.rigatron.rigs4j.BL.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    private int id;
    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
    private Date createDate;
    private Date lastModifiedDate;
    private boolean isEnabled;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_role_mappings",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    public Set<UserRole> getRoles() { return this.roles; }

    @Column(unique = true)
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }
    public boolean getIsEnabled() { return this.isEnabled; }

    public void setId(int value) { this.id = value; }
    public void setUsername(String value) { this.username = value; }
    public void setPassword(String value) { this.password = value; }
    public void setRoles(Set<UserRole> value) { this.roles = value; }
    public void setCreateDate(Date value) { this.createDate = value; }
    public void setLastModifiedDate(Date value) { this.lastModifiedDate = value; }
    public void setIsEnabled(boolean value) { this.isEnabled = value; }
}
