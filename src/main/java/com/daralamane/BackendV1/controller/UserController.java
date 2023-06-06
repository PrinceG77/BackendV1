package com.daralamane.BackendV1.controller;
import com.daralamane.BackendV1.entity.User;
import com.daralamane.BackendV1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Validated

public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all") //ADMIN and EDITOR
    @PreAuthorize("hasAuthority('ADMIN')")
    List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN')")
    ResponseEntity<User> findByUserId(@PathVariable @Min(1) int userId){
        return userService.findByUserId(userId);
    }

    @PostMapping("/add") //ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @PutMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable @Min(1) int userId){
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    ResponseEntity<String> deleteUserById(@PathVariable @Min(1) int userId){
        return userService.deleteUserById(userId);
    }
}
