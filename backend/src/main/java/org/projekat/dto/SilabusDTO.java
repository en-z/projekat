package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SilabusDTO {
    private Long id;
    private String sadrzaj;
    private Long predmetId;
    private Long nastavnikId;
}
