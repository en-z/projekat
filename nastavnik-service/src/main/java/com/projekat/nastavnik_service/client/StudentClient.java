package com.projekat.nastavnik_service.client;

import com.projekat.nastavnik_service.dto.StudentOcenaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "student-service",url = "http://localhost:8083/api/student")
public interface StudentClient {
    @PutMapping("/studenti/predmet/{id}/ocena")
    ResponseEntity<?> upisiOcenu(@PathVariable Long id, @RequestBody List<StudentOcenaDTO> dto);
}
