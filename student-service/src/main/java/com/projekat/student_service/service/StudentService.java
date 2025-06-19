package com.projekat.student_service.service;

import com.projekat.student_service.dto.StudentDTO;
import com.projekat.student_service.entity.Student;
import com.projekat.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAll(){
        return studentRepository.findAll().stream().map(m->new StudentDTO(m)).collect(Collectors.toList());
    }
    public StudentDTO getById(long id){
        Student s =studentRepository.findById(id).orElseThrow(()->new RuntimeException("erro"));
        StudentDTO dto = new StudentDTO(s);
        return dto;
    }
    public StudentDTO getByUserId(long id){
        Student s =studentRepository.findByUserId(id).orElseThrow(()->new RuntimeException("erro"));
        StudentDTO dto = new StudentDTO(s);
        return dto;
    }
    public StudentDTO create(StudentDTO dto){
        Student s = new Student(dto);
        studentRepository.save(s);
        return dto;
    }
    public StudentDTO update(long id,StudentDTO dto){
        Student s =studentRepository.findById(id).orElseThrow(()->new RuntimeException("erro"));
        long iid = s.getId();
        s = new Student(dto);
        s.setId(iid);
        studentRepository.save(s);
        return dto;
    }
    public void delete(long id){
        studentRepository.deleteById(id);
    }
    public List<StudentDTO> search(String ime,String prezime,String brojIndeksa,int godinaUpisa,float minProsek,float maxProsek){
        return studentRepository.search(ime,prezime,brojIndeksa,godinaUpisa,minProsek,maxProsek).stream().map(f->new StudentDTO(f)).collect(Collectors.toList());
    }
}
