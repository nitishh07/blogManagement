package com.nitish.blogManagement.controller;

import com.nitish.blogManagement.model.User;
import com.nitish.blogManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserById(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid  @RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id , @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id , user) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteuser(@PathVariable Integer id){
        return new ResponseEntity<>(userService.deleteUser(id) , HttpStatus.OK);
    }
}
