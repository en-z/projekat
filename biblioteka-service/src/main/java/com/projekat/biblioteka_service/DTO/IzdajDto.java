package com.projekat.biblioteka_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IzdajDto {
    private Long knjigaId;
    private Long userId;
    private String ime;
    private String prezime;
    private Boolean trajan;
    private LocalDate datumVracanja;
}
