package com.diploma.authorization.security;

import com.diploma.authorization.model.Role;
import com.diploma.authorization.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

//        List<Role> roles = null;
//
//        if (roles!=null) {
//            roles = user.getRoles();
////            for (Role role : roles) {
////                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
////            }
//            roles.forEach(t -> {
//                grantedAuthorities.add(new SimpleGrantedAuthority(t.getRole()));
//            });
//        } else {
//            return null;
//        }

        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });



        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    public User getUser(){
        return user;
    }

}
