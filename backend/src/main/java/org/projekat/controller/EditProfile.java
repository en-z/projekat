package org.projekat.controller;

import org.projekat.dto.ProfilDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth/profile")
public class EditProfile {
    @Autowired
    private ProfilService profilService;
    @GetMapping
    public ResponseEntity<ProfilDTO> getProfil()throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails dt = (CustomUserDetails) authentication.getPrincipal();
        long id = dt.getId();
        return profilService.getProfil(id).get();
    }
    @PostMapping
    public CompletableFuture<ResponseEntity<String>> postProfil(){//todo :dovrist
        return CompletableFuture.completedFuture(ResponseEntity.ok("ok"));
    }
}
