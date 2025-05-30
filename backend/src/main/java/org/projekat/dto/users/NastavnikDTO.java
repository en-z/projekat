package org.projekat.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.AngazovanjeDTO;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NastavnikDTO {
    private Long osobaId;
    private String status;
    private List<AngazovanjeDTO> angazovanja;
}

