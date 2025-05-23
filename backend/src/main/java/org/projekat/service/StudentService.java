package org.projekat.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.PredmetDTO;
import org.projekat.dto.PrijavaIspitaDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.model.IshodIspita;
import org.projekat.model.Predmet;
import org.projekat.model.PrijavaIspita;
import org.projekat.model.Student;
import org.projekat.repository.IshodIspitaRepository;
import org.projekat.repository.PredmetRepository;
import org.projekat.repository.PrijavaIspitaRepository;
import org.projekat.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PredmetRepository predmetRepository;
    @Autowired
    private IshodIspitaRepository ishodIspitaRepository;
    @Autowired
    private PrijavaIspitaRepository prijavaIspitaRepository;
    @Async
    public CompletableFuture<List<Student>> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return CompletableFuture.completedFuture(students);
    }
    @Async
    public CompletableFuture<Student> getStudent(long id){ //TODO:(en) doddaj auth extract id check
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student nije pronadjen"));
        return CompletableFuture.completedFuture(student);
    }
    @Async
    public CompletableFuture<Student> saveStudent(Student student){
        return CompletableFuture.completedFuture(studentRepository.save(student));
    }
    @Async
    public CompletableFuture<ResponseEntity<List<PredmetDTO>>> getPredmeteZaUpis(long id){
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student nije pronadjen"));
        int godinaStudija = student.getGodinaStudija();
        int maxSemestar = 2*godinaStudija;// 2*1 = 1 2*2=4 ..6..8
        List<Predmet> predmetList = predmetRepository.findNijePrijavljenIliPolozen(maxSemestar,id,student.getProgram().getId());//TODO(en):godbbolt kad je dereference i mozda optional da se stavi da je ovo
        List<PredmetDTO> predmetDTOS = new ArrayList<>();
        int size = predmetList.size();
        for(int i=0;i<size;i++){
            predmetDTOS.add(new PredmetDTO(predmetList.get(i)));
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(predmetDTOS));
    }
    @Async
    public CompletableFuture<List<IshodIspita>> getIshodIspita(long id){
        List<IshodIspita> ishodIspitaList =ishodIspitaRepository.findAllByStudent_Osoba_id(id);
        return CompletableFuture.completedFuture(ishodIspitaList);
    }
    @Async
    public CompletableFuture<HttpStatus>prijaviIspit(PrijavaIspitaDTO dto, long studentId){
        PrijavaIspita pi = new PrijavaIspita();
        Student s = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("ovo nece puc"));
        pi.setStudent(s);
        pi.setRok(dto.getRok());
        pi.setGodina(dto.getGodina());
        Predmet p = predmetRepository.findById(dto.getPredmetId()).orElseThrow();//en:maybe
        pi.setPredmet(p);
        pi.setDatumPrijave(dto.getDatumPrijave());
        prijavaIspitaRepository.save(pi);

        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }
}
