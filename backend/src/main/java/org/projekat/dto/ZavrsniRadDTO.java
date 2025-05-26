package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZavrsniRadDTO {
    private Long id;
    private String naslov;
    private String opis;
    private String status;
    private Long studentId;
    private Long mentorId;
}