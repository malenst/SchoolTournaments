package com.example.SchoolTournaments.controller;

import com.example.SchoolTournaments.entity.UserEntity;
import com.example.SchoolTournaments.repository.UserRepository;
import com.example.SchoolTournaments.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        // arrange
        Page<UserEntity> expectedUsers = new PageImpl<>(Collections.singletonList(new UserEntity()));
        when(userService.findAll(any(Pageable.class))).thenReturn(expectedUsers);

        // act
        Page<UserEntity> actualUsers = userController.getAllUsers(0, 10, "id");

        // assert
        assertEquals(expectedUsers, actualUsers);
        verify(userService).findAll(any(Pageable.class));
    }

    @Test
    public void testGetUserById() {
        // arrange
        Long userId = 1L;
        UserEntity expectedUser = new UserEntity();
        when(userService.findById(userId)).thenReturn(expectedUser);

        // act
        UserEntity actualUser = userController.getUserById(userId);

        // assert
        assertEquals(expectedUser, actualUser);
        verify(userService).findById(userId);
    }

    @Test
    public void testSearchUserByCriteria() {
        // arrange
        String criteria = "name";
        String input = "John";
        List<UserEntity> expectedUsers = Collections.singletonList(new UserEntity());
        when(userRepository.searchByCriteria(criteria, input)).thenReturn(expectedUsers);

        // act
        List<UserEntity> actualUsers = userController.searchUserByCriteria(criteria, input);

        // assert
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository).searchByCriteria(criteria, input);
    }

    @Test
    public void testCreateUser() {
        // arrange
        UserEntity user = new UserEntity();
        UserEntity expectedUser = new UserEntity();
        when(userService.save(user)).thenReturn(expectedUser);

        // act
        UserEntity actualUser = userController.createUser(user);

        // assert
        assertEquals(expectedUser, actualUser);
        verify(userService).save(user);
    }

    @Test
    public void testUpdateUser() {
        // arrange
        Long userId = 1L;
        UserEntity updatedUser = new UserEntity();
        UserEntity expectedUser = new UserEntity();
        when(userService.update(updatedUser, userId)).thenReturn(expectedUser);

        // act
        UserEntity actualUser = userController.updateUser(userId, updatedUser);

        // assert
        assertEquals(expectedUser, actualUser);
        verify(userService).update(updatedUser, userId);
    }



    @Test
    public void testDeleteUser() {
        // arrange
        Long userId = 1L;

        // act
        userController.deleteUser(userId);

        // assert
        verify(userService).deleteById(userId);
    }
}
