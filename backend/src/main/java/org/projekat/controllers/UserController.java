package org.projekat.controllers;

import org.projekat.dtos.LoginDTO;
import org.projekat.jwt.JwtService;
import org.projekat.model.User;
import org.projekat.repositorys.UserRepository;
import org.projekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/v1")
public class UserController {
    @Autowired
    public UserService service;
    @Autowired
    public UserRepository userRepository; //samo za tesitranje
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "treba na nije secure";
    }
    @PostMapping("/register") // register
    public String addUser(@RequestBody User user){
        return service.addUser(user);
    }
    @PostMapping("/login")// login
    public ResponseEntity<Map<String,String>>authAndGetToken(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
        );
        if (authentication.isAuthenticated()){ //TODO(en):refactor ovaj cancer od koda
            User user = userRepository.findByEmail(loginDTO.getUsername()).orElseThrow(()->new RuntimeException("error"));
            String token = jwtService.generateToken(loginDTO.getUsername(),user.getId());
            Map<String,String> res = new HashMap<>();
            res.put("token",token);
            return ResponseEntity.ok(res);
        }else {
            throw new UsernameNotFoundException("nema korisnika");
        }
    }
    @GetMapping("/student/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/admin/hello")
    public String ahello(){
        return "hello";
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("no"));
    }
}



