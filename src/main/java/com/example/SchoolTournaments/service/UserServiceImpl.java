package com.example.SchoolTournaments.service;

import com.example.SchoolTournaments.entity.Role;
import com.example.SchoolTournaments.entity.UserEntity;
import com.example.SchoolTournaments.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
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
        UserEntity userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return userFromDB;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
