package com.projekat.auth_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private Long userId;
    private String ime;
    private String prezime;
    private String email;
    private Long faklutetId;
    private Long studiskiId;
}
