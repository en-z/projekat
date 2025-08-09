package com.projekat.auth_service.service;

import com.projekat.auth_service.DTO.LoginDTO;
import com.projekat.auth_service.DTO.RegisterDTO;
import com.projekat.auth_service.entity.User;
import com.projekat.auth_service.entity.ZaUpis;
import com.projekat.auth_service.repository.UserRepository;
import com.projekat.auth_service.repository.ZaUpisRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ZaUpisRepostiory zaUpisRepostiory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(RegisterDTO regDto) throws Exception {
        if (userRepository.existsByEmail(regDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email postoji");
        }
        regDto.setPassword(passwordEncoder.encode(regDto.getPassword()));
        User user = new User();
        user.setEmail(regDto.getEmail());
        user.setPassword(regDto.getPassword());
        user.setIme(regDto.getIme());
        user.setPrezime(regDto.getPrezime());
        user.setRoles(regDto.getRoles());
        User res= userRepository.save(user);
        zaUpisRepostiory.save(new ZaUpis(null, regDto.getIme(), regDto.getPrezime(), regDto.getEmail(), user.getId(),regDto.getStudiskiId(), regDto.getFakultetId()));
        regDto.setId(res.getId());
        return ResponseEntity.status(HttpStatus.OK).body(regDto);
    }
}
