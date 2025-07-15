package com.example.service.osoblje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DokumentRequest {
    private MultipartFile file;
    private Long studentId;
    private Long nastavnikId;
    private String naslov;
    private String opis;
}
