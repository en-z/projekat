package org.projekat.mapper;

import org.projekat.dto.SlusanjePredmetaDTO;
import org.projekat.mapper.users.StudentMapper;
import org.projekat.model.Predmet;
import org.projekat.model.SlusanjePredmeta;
import org.projekat.model.Student;

public class SlusanjePredmetaMapper {

    public static SlusanjePredmetaDTO toDTO(SlusanjePredmeta slusanje) {
        return new SlusanjePredmetaDTO(
                slusanje.getId(),
                slusanje.getPredmet().getId(),
                StudentMapper.toDTO(slusanje.getStudent())
        );
    }

    public static SlusanjePredmeta fromDTO(SlusanjePredmetaDTO dto, Student student, Predmet predmet) {
        SlusanjePredmeta s = new SlusanjePredmeta();
        s.setId(dto.getId());
        s.setStudent(student);
        s.setPredmet(predmet);
        return s;
    }
}
