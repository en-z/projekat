package com.projekat.admin_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NastavnikDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String biografija;
    private String status;
}
