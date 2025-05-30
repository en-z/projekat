package org.projekat.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.projekat.dto.IshodIspitaList;
import org.projekat.model.IshodIspita;
import org.projekat.repository.IshodIspitaRepository;
import org.projekat.repository.PredmetRepository;
import org.projekat.repository.users.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UploadXmlService {
    @Autowired
    private IshodIspitaRepository ishodIspitaRepository;
    @Autowired
    private PredmetRepository predmetRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadXml(MultipartFile file){
        try(InputStream is = file.getInputStream()){
            JAXBContext c = JAXBContext.newInstance(IshodIspita.class);
            Unmarshaller m= c.createUnmarshaller();
            IshodIspitaList wrap = (IshodIspitaList) m.unmarshal(is);
            List<IshodIspita> ishodi = wrap.getIshodi();
            for(IshodIspita i :ishodi){
                long pid = i.getPredmet().getId();
                i.setPredmet(predmetRepository.findById(pid).orElseThrow(()->new IllegalArgumentException("nije validan predmet id"+pid)));
                long sid = i.getStudent().getOsoba().getUser().getId();
                i.setStudent(studentRepository.findById(sid).orElseThrow(()->new IllegalArgumentException("nije validan student id"+sid)));
            }
            ishodIspitaRepository.saveAll(ishodi);
            return CompletableFuture.completedFuture(ResponseEntity.ok("saved "+ishodi.size()));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Error: "+e.getMessage()));
        }
    }
}
