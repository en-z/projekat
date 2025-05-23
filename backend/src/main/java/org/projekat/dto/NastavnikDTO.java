package org.projekat.dto;

import lombok.Data;

import java.util.List;

@Data
public class NastavnikDTO {
    private Long osobaId;
    private String status;
    private List<AngazovanjeDTO> angazovanja;
}

