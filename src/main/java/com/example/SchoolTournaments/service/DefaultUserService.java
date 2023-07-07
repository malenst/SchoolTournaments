package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.model.UserEntity;
import com.example.SchoolTournaments.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity update(UserEntity user, Long id) {
        Optional<UserEntity> userFromDBOptional = userRepository.findById(id);
        if (userFromDBOptional.isPresent()) {
            UserEntity userFromDB = userFromDBOptional.get();
            userFromDB.setName(user.getName());
            userFromDB.setLastName(user.getLastName());
            userFromDB.setPatronymic(user.getPatronymic());
            userFromDB.setBirthDay(user.getBirthDay());
            userFromDB.setSchool(user.getSchool());
            userFromDB.setGrade(user.getGrade());
            userFromDB.setCity(user.getCity());
            userFromDB.setEmail(user.getEmail());
            userFromDB.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(userFromDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
