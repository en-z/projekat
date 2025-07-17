package com.example.service.osoblje.controller;

import com.example.service.osoblje.client.AuthClient;
import com.example.service.osoblje.dto.OsobaRegDto;
import com.example.service.osoblje.dto.RegisterDTO;
import com.example.service.osoblje.models.Osoblje;
import com.example.service.osoblje.service.OsobljeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.List;

@Controller
@RequestMapping("/api/osoblje/osoblje")
public class OsobljeController {
    @Autowired
    private OsobljeService osobljeService;
    @Autowired
    private AuthClient authClient;
    @GetMapping
    public List<Osoblje> getAll(){
        return osobljeService.findAll();
    }
    @GetMapping("{id}")
    public Osoblje findById(@PathVariable Long id){
        return osobljeService.findById(id);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<Osoblje> post(@RequestBody OsobaRegDto os){
        RegisterDTO dto = authClient.register(new RegisterDTO(os)).getBody();
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Osoblje o =new Osoblje();
        o.setFakultetId(os.getFakultetId());
        o.setUserId(dto.getId());
        return new ResponseEntity<>(osobljeService.save(o),HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        osobljeService.deleteById(id);
    }
}
