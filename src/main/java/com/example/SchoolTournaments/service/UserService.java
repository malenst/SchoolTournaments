package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    Page<UserEntity> findAll(Pageable pageable);
    UserEntity findById(Long id);
    UserEntity save(UserEntity user);
    UserEntity update(UserEntity user, Long id);
    void deleteById(Long id);
    List<UserEntity> search(String fragment);
}