package com.example.SchoolTournaments.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;



public enum Role implements GrantedAuthority {
    ADMINISTRATOR("ROLE_ADMIN"),
    TEACHER("ROLE_TEACHER"),
    SCHOOLBOY("ROLE_SCHOOLBOY");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}