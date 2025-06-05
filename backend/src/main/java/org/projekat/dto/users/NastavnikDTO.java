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
    private Long id;
    private String ime;
    private String prezime;
    private String biografija;
    private String status;
    private List<AngazovanjeDTO> angazovanja;
}

