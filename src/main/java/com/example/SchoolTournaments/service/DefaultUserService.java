package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.model.UserModel;
import com.example.SchoolTournaments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserModel findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public UserModel update(UserModel user, Long id) {
        Optional<UserModel> userFromDBOptional = userRepository.findById(id);
        if (userFromDBOptional.isPresent()) {
            UserModel userFromDB = userFromDBOptional.get();
            userFromDB.setName(user.getName());
            userFromDB.setLastName(user.getLastName());
            userFromDB.setPatronymic(user.getPatronymic());
            userFromDB.setBirthDay(user.getBirthDay());
            userFromDB.setSchool(user.getSchool());
            userFromDB.setGrade(user.getGrade());
            userFromDB.setCity(user.getCity());
            userFromDB.setEmail(user.getEmail());
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
