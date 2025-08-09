package com.projekat.auth_service.service;

import com.projekat.auth_service.DTO.ImeDTO;
import com.projekat.auth_service.DTO.LoginDTO;
import com.projekat.auth_service.DTO.StudentDTO;
import com.projekat.auth_service.Security.CustomUserDetails;
import com.projekat.auth_service.entity.User;
import com.projekat.auth_service.repository.UserRepository;
import com.projekat.auth_service.repository.ZaUpisRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ZaUpisRepostiory zaUpisRepostiory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails= userRepository.findByEmail(username);
        return userDetails.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findById(Long id) {
        User u = userRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
        u.setPassword("");
        return u;
    }
    public List<ImeDTO> findByRole(){
       return userRepository.findByRolesContaining("ROLE_USER").stream().map(m->new ImeDTO(m.getId(),m.getIme(),m.getPrezime())).toList();
    }
    public Page<StudentDTO> getZaUpis(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return zaUpisRepostiory.findByFakultetId(id,pageable).map(z->new StudentDTO(z.getUserId(),z.getIme(),z.getPrezime(),z.getEmail(),z.getFakultetId(),z.getStudiskiId()));
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Nema korisnika sa tim mailom"));
    }

    public List<User> findByEmailCnx(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public HttpStatus resetSifru(long id ,User user){
        User nuser = userRepository.findById(id).orElseThrow(()->new RuntimeException("Nema korisnika sa tim id"));
        nuser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(nuser);
        return HttpStatus.OK;
    }


}