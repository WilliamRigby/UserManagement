package com.rigatron.rigs4j.BL.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Access(AccessType.PROPERTY)
@Table(name="user_roles")
public class UserRole {

    private int id;
    private String role;
    private Set<User> users = new HashSet<>();

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() { return this.id; }

    public String getRole() { return this.role; }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() { return this.users; }

    public void setId(int value) { this.id = value; }
    public void setRole(String value) { this.role = value; }
    public void setUsers(Set<User> value) { this.users = value; }
}
