package com.projekat.nastavnik_service.client;

import com.projekat.nastavnik_service.dto.PredmetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "admin-service",url = "http://localhost:8082/api/admin")
public interface AdminClient {
    @GetMapping("/predmeti/by-ids")
    ResponseEntity<List<PredmetDTO>> getPredmetiByIds(@RequestParam List<Long> ids);
}
