package com.projekat.nastavnik_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZavrsniRadDTO {
    private long studentId;
    private long nastavnikId;
    private String opis;
    private String naslov;
    private MultipartFile file;
}
