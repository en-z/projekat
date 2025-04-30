package org.projekat.service;

import org.projekat.dtos.RegisterDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.model.Adresa;
import org.projekat.model.Osoba;
import org.projekat.model.User;
import org.projekat.repositorys.AdresaRepository;
import org.projekat.repositorys.OsobaRepository;
import org.projekat.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails= userRepository.findByEmail(username);
        return userDetails.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }
}
