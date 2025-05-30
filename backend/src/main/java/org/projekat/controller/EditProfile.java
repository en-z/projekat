package org.projekat.controller;

import org.projekat.dto.ProfilDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
        List<String> rolse = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return profilService.getProfil(id,rolse).get();
    }
    @PostMapping
    public ResponseEntity<?> postProfil(@RequestBody ProfilDTO dto)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails dt = (CustomUserDetails) authentication.getPrincipal();
        long id = dt.getId();
        return profilService.editProfil(dto,id).get();
    }
}
