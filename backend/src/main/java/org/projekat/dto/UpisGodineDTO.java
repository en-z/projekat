package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpisGodineDTO {
    private Long id;
    private int godinaStudija;
    private Date datumUpisa;
    private String status;
    private Long studentId;
}
