package com.projekat.student_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nastavnik-service",url = "http://localhost:8084/api/nastavnik")
public interface NastavnikClient {
    @GetMapping("/ishodi/student/{id}")
    ResponseEntity<?> getIshode(@PathVariable long id);
    @GetMapping("/kolokvijumi/predmet/{predmetId}/student/{studentId}")
    ResponseEntity<?>getPredmeteZaStudenta(@PathVariable  Long studentId,@PathVariable Long predmetId);
}
