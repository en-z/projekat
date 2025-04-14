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
    public void register(@RequestParam String email,@RequestParam String password){
        user.Register(email,password);
    }
    }
}

