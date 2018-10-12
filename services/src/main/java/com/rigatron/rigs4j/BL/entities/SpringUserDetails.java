package com.rigatron.rigs4j.BL.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SpringUserDetails implements UserDetails {

    private User user;
    private Collection<GrantedAuthority> grantedAuthorities;

    public SpringUserDetails(User user) {

        this.user = user;

        List<GrantedAuthority> authorities = new java.util.ArrayList<>();

        for(UserRole role : user.roles) {
            authorities.add(new SimpleGrantedAuthority(role.role));
        }

        this.grantedAuthorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.user.password;
    }

    @Override
    public String getUsername() {
        return this.user.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }
}
