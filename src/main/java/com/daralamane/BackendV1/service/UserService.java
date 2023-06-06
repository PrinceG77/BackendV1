package com.daralamane.BackendV1.service;

import com.daralamane.BackendV1.entity.User;
import com.daralamane.BackendV1.exception.ResourceNotFoundException;
import com.daralamane.BackendV1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> findByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User Id : %d is not found", userId)));
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<User> createUser(User user) {
        String encPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPwd);
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> updateUser(User userData, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User Id : %d is not found", userId)));
        user.setUserId(userId);

        user.setUsername(userData.getUsername());
        if (!userData.getPassword().equals(user.getPassword())) {
            String encPwd = bCryptPasswordEncoder.encode(userData.getPassword());
            user.setPassword(encPwd);
        }
        user.setRoleSet(userData.getRoleSet());

        return ResponseEntity.ok(userRepository.save(user));

    }

    @Transactional
    public ResponseEntity<String> deleteUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User Id : %d is not found", userId)));
        userRepository.deleteById(user.getUserId());
        return ResponseEntity.ok().body("User id: "+userId+" is deleted.");
    }
}
