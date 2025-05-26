package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.users.StudentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlusanjePredmetaDTO {
    private Long id;
    private Long predmetId;
    private StudentDTO student;
}
