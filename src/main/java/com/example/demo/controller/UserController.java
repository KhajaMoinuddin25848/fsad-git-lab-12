package com.example.demo.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository repo;

    // CREATE
    @PostMapping
    public User createUser(@RequestBody User user) {
        return repo.save(user);
    }

    // READ
    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {

        User existing = repo.findById(id).get();
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repo.save(existing);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        repo.deleteById(id);
        return "User Deleted";
    }   
}