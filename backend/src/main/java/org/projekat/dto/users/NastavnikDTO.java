package org.projekat.dto.users;

import lombok.Data;
import org.projekat.dto.AngazovanjeDTO;

import java.util.List;

@Data
public class NastavnikDTO {
    private Long osobaId;
    private String status;
    private List<AngazovanjeDTO> angazovanja;
}

