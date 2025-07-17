package com.example.service.osoblje.client;

import com.example.service.osoblje.dto.RegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service",url = "http://localhost:8081/api/auth")
public interface AuthClient {
    @PostMapping("/registration")
    ResponseEntity<RegisterDTO> register(RegisterDTO dto);
    @DeleteMapping("/{id}")
    void delete(long id);
}
