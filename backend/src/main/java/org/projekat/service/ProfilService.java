package org.projekat.service;

import org.projekat.dto.ProfilDTO;
import org.projekat.model.User;
import org.projekat.repository.NastavnikRepository;
import org.projekat.repository.StudentRepository;
import org.projekat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProfilService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Async
    public CompletableFuture<ResponseEntity<ProfilDTO>> getProfil(long id){
        ProfilDTO profilDTO = new ProfilDTO();
        if(studentRepository.existsById(id)){
            profilDTO.setData(studentRepository.findById(id));
            profilDTO.setId(id);
            profilDTO.setRole("STUDENT");
        } else if (nastavnikRepository.existsById(id)) {
            profilDTO.setData(nastavnikRepository.findById(id));
            profilDTO.setId(id);
            profilDTO.setRole("NASTAVNIk");
        } else if (userRepository.existsById(id)){
            profilDTO.setRole("ADMIN");
            profilDTO.setId(id);
            profilDTO.setData(userRepository.findById(id));
        }else {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CONFLICT).build());
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(profilDTO)); //en:nece provjeravat branchove nakon prvog hita i godbolt oce li inline prvi branch
    }
}
