package org.projekat.controllers;

import org.projekat.model.User;
import org.projekat.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @PutMapping("/{id}")
    public User putUser(@PathVariable long id,@RequestBody User userNew){
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        user.setEmail(userNew.getEmail());
        user.setPassword(userNew.getPassword());
        return userRepository.save(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }
}


