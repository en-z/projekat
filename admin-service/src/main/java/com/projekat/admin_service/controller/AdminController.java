package com.projekat.admin_service.controller;

import com.projekat.admin_service.DTO.AdminDTO;
import com.projekat.admin_service.DTO.AdminResponse;
import com.projekat.admin_service.entity.Admin;
import com.projekat.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/admin/admin")
    public class AdminController {

        @Autowired
        private AdminService adminService;

        @GetMapping
        public ResponseEntity<List<Admin>> getAll() {
            return ResponseEntity.ok(adminService.getAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Admin> getById(@PathVariable long id) {
            return ResponseEntity.ok(adminService.getById(id));
        }

        @PostMapping
        public ResponseEntity<Admin> create(@RequestBody AdminResponse dto) {
            Admin created = adminService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }

        @PutMapping
        public ResponseEntity<Admin> update(@RequestBody AdminDTO dto) {
            Admin updated = adminService.put(dto);
            return ResponseEntity.ok(updated);
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<Void> delete(@PathVariable long userId) {
            adminService.delete(userId);
            return ResponseEntity.noContent().build();
        }
    }

