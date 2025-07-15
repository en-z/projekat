package com.projekat.student_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "auth-service",url = "http://localhost:8081/api/auth")
public interface AuthClient {
    @PutMapping("/{id}/student")
    ResponseEntity<?> dodajRole(@PathVariable long id);
}
