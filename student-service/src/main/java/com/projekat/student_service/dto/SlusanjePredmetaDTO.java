package com.projekat.student_service.dto;

import com.projekat.student_service.entity.SlusanjePredmeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlusanjePredmetaDTO {
    private Long id;
    private Long predmetId;
    private StudentDTO student;
    public SlusanjePredmetaDTO(SlusanjePredmeta s){
        this.id = s.getId();
        this.predmetId = s.getPredmetId();
        this.student = new StudentDTO(s.getStudent());
    }
}
