package com.projekat.student_service.service;

import com.projekat.student_service.client.AuthClient;
import com.projekat.student_service.dto.AddDTO;
import com.projekat.student_service.dto.StudentDTO;
import com.projekat.student_service.entity.Student;
import com.projekat.student_service.repository.StudentRepository;
import feign.FeignException;
import feign.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AuthClient authClient;

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
    @Transactional
    public Student create(AddDTO dto){
        Student s = new Student(dto);
        s.setAktivan(true);
        s = studentRepository.save(s);
        s.setBrojIndeksa(StudentService.getBrojIndeksa(s.getGodinaUpisa(),s.getId()));
        authClient.dodajRole(s.getUserId());
        return s;
    }
    public StudentDTO update(long id,StudentDTO dto){
        Student s =studentRepository.findById(id).orElseThrow(()->new RuntimeException("erro"));
        long iid = s.getId();
        s = new Student(dto);
        s.setId(iid);
        studentRepository.save(s);
        return dto;
    }
    public void ocena(long id,float ocena){
       Student s = studentRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
       s.setProsecnaOcena(ocena);
       studentRepository.save(s);
    }
    public void delete(long id){
        Student s = studentRepository.findById(id).orElse(null);
        if( s ==null){
            throw new RuntimeException("No user id");
        }
        s.setAktivan(false);
        studentRepository.save(s);
    }
    public List<StudentDTO> search(
            String ime,
            String prezime,
            String brojIndeksa,
            Integer godinaUpisa,
            Integer godinaStudija,
            Long studiskiId,
            Float prosekMin,
            Float prosekMax,
            Integer esbpMin,
            Integer esbpMax,
            Boolean aktivan,
            String ulica,
            String broj,
            String grad,
            String drzava
    ){
        return studentRepository.search(
                 ime,
                 prezime,
                 brojIndeksa,
                 godinaUpisa,
                 godinaStudija,
                 studiskiId,
                 prosekMin,
                 prosekMax,
                 esbpMin,
                 esbpMax,
                 aktivan,
                 ulica,
                 broj,
                 grad,
                 drzava
        ).stream().map(f->new StudentDTO(f)).toList();
    }
    private static String getBrojIndeksa(int y, long id){
        String format = String.format("%06d",id);
       return y+format;
    }
}
