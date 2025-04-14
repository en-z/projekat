package org.projekat.controllers;

import org.projekat.fields.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final User user;
    public UserController(User user){
        this.user = user;
    }
    @PostMapping("/register")
    public void register(@RequsetParam String email,@RequstParam String password){
        user.Register(email,password);
    }
    @GetMapping("/isRegistered")
    public boolean isRegistered(@RequestParam String email,@RequestParam String password){
        return user.isRegistered(email,password);
    }
}

