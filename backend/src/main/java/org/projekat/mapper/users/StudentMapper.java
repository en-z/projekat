package org.projekat.mapper.users;

import org.projekat.dto.users.StudentDTO;
import org.projekat.model.users.Student;

public class StudentMapper {

    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setBrojIndeksa(student.getBrojIndeksa());
        dto.setGodinaUpisa(student.getGodinaUpisa());
        dto.setProsecnaOcena(student.getProsecnaOcena());
        dto.setOsvojeniESPB(student.getOsvojeniESPB());

        if (student.getOsoba() != null) {
            dto.setIme(student.getOsoba().getIme());
            dto.setPrezime(student.getOsoba().getPrezime());
            if (student.getOsoba().getUser() != null) {
                dto.setEmail(student.getOsoba().getUser().getEmail());
            }
        }

        return dto;
    }

    public static Student fromDTO(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setBrojIndeksa(dto.getBrojIndeksa());
        student.setGodinaUpisa(dto.getGodinaUpisa());
        student.setProsecnaOcena(dto.getProsecnaOcena());
        student.setOsvojeniESPB(dto.getOsvojeniESPB());
        return student;
    }
}
