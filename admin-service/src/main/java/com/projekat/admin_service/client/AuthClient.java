package com.projekat.admin_service.client;

import com.projekat.admin_service.DTO.RegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service",url = "http://localhost:8081/api/auth")
public interface AuthClient {
    @PostMapping("/registration")
    ResponseEntity<RegisterDTO> registruj(@RequestBody RegisterDTO dto);
    @DeleteMapping("/user/{id}")
    void delete(@PathVariable long id);
}
