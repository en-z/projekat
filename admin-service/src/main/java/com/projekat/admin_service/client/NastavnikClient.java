package com.projekat.admin_service.client;

import com.projekat.admin_service.DTO.NastavnikDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="nastavnik-service",url = "http://localhost:8084/api/nastavnik")
public interface NastavnikClient {
    @GetMapping("/nastavnici/{id}")
    ResponseEntity<NastavnikDTO> getNastavnikById(@PathVariable long id);
}
