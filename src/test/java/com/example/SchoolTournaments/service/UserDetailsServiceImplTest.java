package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.entity.UserEntity;
import com.example.SchoolTournaments.repository.UserRepository;

import com.example.SchoolTournaments.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // arrange
        UserEntity user = new UserEntity();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.SCHOOLBOY);
        user.setRoles(roles);
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(user);

        // act
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        // assert
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(user.getRoles().size(), userDetails.getAuthorities().size());
    }

    @Test
    public void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        // arrange
        Mockito.when(userRepository.findByUsername("nonExistingUser")).thenReturn(null);

        // act + assert
        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("nonExistingUser")
        );
    }

    @Test
    public void getAuthorities_UserHasRoles_ReturnsAuthorities() {
        // arrange
        UserEntity user = new UserEntity();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.SCHOOLBOY);
        user.setRoles(roles);

        // act
        Collection<? extends GrantedAuthority> authorities = userDetailsService.getAuthorities(user);
        System.out.println(authorities);

        // assert
        assertEquals(1, authorities.size());
        System.out.println(new SimpleGrantedAuthority(Role.SCHOOLBOY.getAuthority()));
        //assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.SCHOOLBOY.getAuthority())));
    }
}
