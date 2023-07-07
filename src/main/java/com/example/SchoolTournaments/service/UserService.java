package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserEntity> findAll(Pageable pageable);
    UserEntity findById(Long id);
    UserEntity save(UserEntity user);
    UserEntity update(UserEntity user, Long id);
    void deleteById(Long id);

}