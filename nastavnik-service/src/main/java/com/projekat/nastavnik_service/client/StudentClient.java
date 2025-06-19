package com.projekat.nastavnik_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "student-service",url = "http://localhost:8083/api/student")
public interface StudentClient {
    @PutMapping("/studenti/prosecna")
    ResponseEntity<?> upisiOcenu(@RequestParam long id, @RequestParam float ocena);
}
