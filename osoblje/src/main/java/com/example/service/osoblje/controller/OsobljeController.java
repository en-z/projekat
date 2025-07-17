package com.example.service.osoblje.controller;

import com.example.service.osoblje.models.Osoblje;
import com.example.service.osoblje.service.OsobljeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/osoblje/osoblje")
public class OsobljeController {
    @Autowired
    OsobljeService osobljeService;
    @GetMapping
    public List<Osoblje> getAll(){
        return osobljeService.findAll();
    }
    @GetMapping("{id}")
    public Osoblje findById(@PathVariable Long id){
        return osobljeService.findById(id);
    }
    @PostMapping
    public Osoblje post(@RequestBody Osoblje os){
        return osobljeService.save(os);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        osobljeService.deleteById(id);
    }
}
