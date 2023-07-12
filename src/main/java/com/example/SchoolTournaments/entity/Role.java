package com.example.SchoolTournaments.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;



public enum Role implements GrantedAuthority {
    ADMINISTRATOR,
    TEACHER,
    SCHOOLBOY;

    @Override
    public String getAuthority() {
        return name();
    }
}