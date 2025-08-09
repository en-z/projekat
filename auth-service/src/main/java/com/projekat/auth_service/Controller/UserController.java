package com.projekat.auth_service.Controller;

import com.projekat.auth_service.DTO.EditDTO;
import com.projekat.auth_service.DTO.ImeDTO;
import com.projekat.auth_service.DTO.LoginDTO;
import com.projekat.auth_service.DTO.RegisterDTO;
import com.projekat.auth_service.Security.CustomUserDetails;
import com.projekat.auth_service.Security.JwtService;
import com.projekat.auth_service.entity.User;
import com.projekat.auth_service.entity.ZaUpis;
import com.projekat.auth_service.repository.UserRepository;
import com.projekat.auth_service.repository.ZaUpisRepostiory;
import com.projekat.auth_service.service.RegisterService;
import com.projekat.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    public UserService service;
    @Autowired
    public UserRepository userRepository; //samo za tesitranje
    @Autowired
    public ZaUpisRepostiory zaUpisRepostiory;
    @Autowired
    public RegisterService registerService; //samo za tesitranje
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;
    @GetMapping
    private ResponseEntity<?> get(@AuthenticationPrincipal CustomUserDetails userDetails){
        long id = userDetails.getId();
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    private ResponseEntity<?> post(@RequestBody EditDTO dto, @AuthenticationPrincipal CustomUserDetails userDetails){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        User u =service.findById(id);
        u.setPassword(dto.getPassword());
        u.setEmail(dto.getEmail());
        return ResponseEntity.ok(service.save(u));
    }
    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody RegisterDTO user)throws Exception{
        List<String>s = new ArrayList<>();
        s.add("ROLE_USER");
        user.setRoles(s);
        return registerService.addUser(user);
    }
    @PostMapping("/login")// login
    public ResponseEntity<Map<String,String>>authAndGetToken(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        if (authentication.isAuthenticated()){ //TODO(en):refactor ovaj cancer od koda
            User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new RuntimeException("error"));
            String token = jwtService.generateToken(loginDTO.getEmail(),user);
            Map<String,String> res = new HashMap<>();
            res.put("token",token);
            return ResponseEntity.ok(res);
        }else {
            throw new UsernameNotFoundException("nema korisnika");
        }
    }
    @GetMapping("/ime/{id}")
    public ResponseEntity<ImeDTO> getIme(@PathVariable long id){
        User u =userRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
        ImeDTO i= new ImeDTO(u.getIme(),u.getPrezime());
        return ResponseEntity.ok(i);
    }
    @GetMapping("/zaupis/{id}")
    public ResponseEntity<?> getStudente(@PathVariable  Long id,@RequestParam(defaultValue = "0") int page ,@RequestParam(defaultValue = "25") int size){
        return ResponseEntity.ok(service.getZaUpis(id,page,size));
    }
    @PutMapping("/{id}/student")
    public ResponseEntity<?> dodajRole(@PathVariable long id){
        User u = userRepository.findById(id).orElseThrow(()->new RuntimeException("NULL"));
        List<String>roles = new ArrayList<>();
        roles.add("ROLE_STUDENT");
        u.setRoles(roles);
        userRepository.save(u);
        ZaUpis z = zaUpisRepostiory.findByUserId(id);
        zaUpisRepostiory.delete(z);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/sifarnik")
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/sifarnik/by-email")
    public ResponseEntity<User>getByEmail(@RequestParam String email)throws Exception{
        return ResponseEntity.ok(service.findByEmail(email));
    }
    @GetMapping("/sifarnik/byemail")
    public ResponseEntity<List<User>>getByEmailCtx(@RequestParam String email)throws Exception{
        return ResponseEntity.ok(service.findByEmailCnx(email));
    }
    @PutMapping("/sifarnik/{id}")
    public HttpStatus putUser(@PathVariable long id, @RequestBody User newUser) throws Exception{
        return service.resetSifru(id,newUser);
    }

}
