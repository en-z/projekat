package com.projekat.biblioteka_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service",url = "http://localhost:8081/api/auth")
public interface AuthClient {
    @GetMapping("/ime/{id}")
    ResponseEntity<ImeDTO> getIme(@PathVariable long id);
}
