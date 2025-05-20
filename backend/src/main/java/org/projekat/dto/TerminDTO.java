package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminDTO {
    private Long id;
    private String tema;
    private LocalDateTime datum;
    private Long predmetId;
    private Long nastavnikId;
}
