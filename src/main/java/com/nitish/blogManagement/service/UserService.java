package com.nitish.blogManagement.service;

import com.nitish.blogManagement.model.User;
import com.nitish.blogManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Integer id , User user) {
        User existinguser = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        existinguser.setName(user.getName());
        existinguser.setEmail(user.getEmail());
        existinguser.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepo.save(existinguser);
    }

    public String deleteUser(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepo.delete(user);
        return "User deleted successfully";
    }
}
