package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserModel> findAll(Pageable pageable);
    UserModel findById(Long id);
    UserModel save(UserModel user);
    void deleteById(Long id);


}