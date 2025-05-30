package org.projekat.controller;

import org.projekat.dto.AdminDTO;
import org.projekat.model.*;
import org.projekat.repository.users.UserRepository;
import org.projekat.service.AdminService;
import org.projekat.service.UniverzitetService;
import org.projekat.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UniverzitetService univerzitetService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping
    public String hello(){
        return "hello" ;
    }
    @GetMapping("/sifarnik")
    public ResponseEntity<User>getByEmail(@RequestParam String email)throws Exception{
        System.out.println("Before async call, auth: " + SecurityContextHolder.getContext().getAuthentication());
        return adminService.grabByEmail(email).get();
    }
    @GetMapping("/user/{id}")
    public User geta(@PathVariable long id) throws Exception{
        return adminService.getUserByIdAsync(id).get();
    }
    @PutMapping("/sifarnik/{id}")
    public HttpStatus putUser(@PathVariable long id, @RequestBody User newUser) throws Exception{
        return adminService.resetSifru(id,newUser).get();
    }
    @PostMapping("/addUser")
    public HttpStatus postUser(@RequestBody AdminDTO dto)throws Exception{
        if(dto.getUserType().contains("admin")){// dodaj ili nastavnika
           return adminService.dodajAdmina(dto.adminFromDTO(dto)).get();// clean

        }else if (dto.getUserType().contains("nastavnik")){
           return adminService.dodajNastavnika(dto.nastavnikFromDto(dto)).get();//clean

        }
        return HttpStatus.BAD_REQUEST;
    }
}
