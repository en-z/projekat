package org.projekat.controller.users;

import org.projekat.dto.LoginDTO;
import org.projekat.dto.RegisterDTO;
import org.projekat.jwt.JwtService;
import org.projekat.model.User;
import org.projekat.repository.users.UserRepository;
import org.projekat.service.RegisterService;
import org.projekat.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    public UserService service;
    @Autowired
    public UserRepository userRepository; //samo za tesitranje
    @Autowired
    public RegisterService registerService; //samo za tesitranje
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/registration") // izbacit u poseban mapping
    public CompletableFuture<ResponseEntity<?>>addUser(@RequestBody RegisterDTO user)throws Exception{
        return registerService.addUser(user);
    }
    @PostMapping("/login")// login
    public ResponseEntity<Map<String,String>>authAndGetToken(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
        );
        if (authentication.isAuthenticated()){ //TODO(en):refactor ovaj cancer od koda
            User user = userRepository.findByEmail(loginDTO.getUsername()).orElseThrow(()->new RuntimeException("error"));
            String token = jwtService.generateToken(loginDTO.getUsername(),user);
            Map<String,String> res = new HashMap<>();
            res.put("token",token);
            return ResponseEntity.ok(res);
        }else {
            throw new UsernameNotFoundException("nema korisnika");
        }
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("no"));
    }
}



