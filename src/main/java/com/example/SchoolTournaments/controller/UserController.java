package com.example.SchoolTournaments.controller;

import com.example.SchoolTournaments.service.UserService;
import com.example.SchoolTournaments.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public Page<UserModel> getAllUsers(
            @Parameter(description = "Page number", example = "0", required = false) @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Page size", example = "10", required = false) @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "Field to sort by", example = "id", required = false) @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public UserModel getUserById(@Parameter(description = "User ID", example = "1L", required = true) @PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create user")
    public UserModel createUser(@Parameter(description = "User's data", required = true)@RequestBody UserModel user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user's data")
    public UserModel updateUser(@Parameter(description = "User ID", example = "1L", required = true) @PathVariable Long id, @Parameter(description = "User with changed data", required = true) @RequestBody UserModel updatedUser) {
        return userService.update(updatedUser, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user from database")
    public void deleteUser(@Parameter(description = "User ID", example = "1L", required = true) @PathVariable Long id) {
        userService.deleteById(id);
    }
}
