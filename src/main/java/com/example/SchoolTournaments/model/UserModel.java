package com.example.SchoolTournaments.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Schema(description = "User of product")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique user ID")
    private Long id;
    @Schema(description = "First name of user")
    private String name;
    @Schema(description = "Last name of user")
    private String lastName;
    @Schema(description = "Patronymic of user")

    private String patronymic;
    @Schema(description = "User's date of birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    @Schema(description = "User's school")
    private String school;
    @Schema(description = "User's grade of the school")
    private String grade;

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Schema(description = "User's city")
    private String city;
    @Schema(description = "User's email")
    private String email;
    @Schema(description = "User's phone number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel(Long id, String name, String lastName, String patronymic, LocalDate birthDay, String school, String grade, String city, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
        this.school = school;
        this.grade = grade;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public UserModel() {}
}
