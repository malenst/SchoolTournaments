package com.example.SchoolTournaments.repository;

import com.example.SchoolTournaments.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryForSearch {
    List<UserEntity> searchByCriteria(String searchParam, String searchValue);
    UserEntity findByUsername(String username);
    @Query("SELECT u FROM users u WHERE " +
            "u.name LIKE %:fragment% OR " +
            "u.lastName LIKE %:fragment% OR " +
            "u.patronymic LIKE %:fragment% OR " +
            "u.school LIKE %:fragment% OR " +
            "u.grade LIKE %:fragment% OR " +
            "u.city LIKE %:fragment%")
    List<UserEntity> search(@Param("fragment") String fragment);
}
