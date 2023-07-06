package com.example.SchoolTournaments.controller;

import com.example.SchoolTournaments.model.UserModel;
import com.example.SchoolTournaments.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    @Test
    public void testGetAllUsers() throws Exception {

        UserModel user = new UserModel();
        user.setId(1L);
        user.setName("Nikita");
        Page<UserModel> page = new PageImpl<>(Collections.singletonList(user));

        Mockito.when(userService.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Nikita"));
    }

    @Test
    public void testUserById() throws Exception {

        UserModel user = new UserModel();
        user.setId(1L);
        user.setName("Nikita");

        Mockito.when(userService.findById(Mockito.any(Long.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nikita"));
    }

    @Test
    public void testUpdateUser() throws Exception {

        UserModel user = new UserModel();
        user.setId(1L);
        user.setName("Nikita");
        user.setBirthDay(LocalDate.of(1666, 11, 7));

        UserModel user2 = new UserModel();
        user2.setId(1L);
        user2.setName("Not_Nikita");
        user.setBirthDay(LocalDate.of(1966, 11, 7));

        Mockito.when(userService.findById(Mockito.any(Long.class))).thenReturn(user);
        Mockito.when(userService.update(Mockito.any(UserModel.class), Mockito.eq(user.getId()))).thenReturn(user2);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDay").value(user2.getBirthDay()));
    }

}
