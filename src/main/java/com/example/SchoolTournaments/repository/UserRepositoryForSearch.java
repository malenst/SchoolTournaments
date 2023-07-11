package com.example.SchoolTournaments.repository;

import com.example.SchoolTournaments.entity.UserEntity;

import java.util.List;

public interface UserRepositoryForSearch {
    List<UserEntity> searchByCriteria(String searchParam, String searchValue);
}
