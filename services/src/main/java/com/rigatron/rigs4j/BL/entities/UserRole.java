package com.rigatron.rigs4j.BL.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user_roles")
public class UserRole {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
