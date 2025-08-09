package com.projekat.student_service.service;

import com.projekat.student_service.client.AdminClient;
import com.projekat.student_service.dto.PredmetDTO;
import com.projekat.student_service.dto.SlusanjaReqDto;
import com.projekat.student_service.dto.SlusanjePredmetaDTO;
import com.projekat.student_service.entity.SlusanjePredmeta;
import com.projekat.student_service.entity.Student;
import com.projekat.student_service.repository.SlusanjePredmetaRepository;
import com.projekat.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SlusanjePredmetaService {
    @Autowired
    private SlusanjePredmetaRepository slusanjePredmetaRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminClient adminClient;
    public List<SlusanjePredmetaDTO> getStudentiNaPredmetu(long id){
        List<SlusanjePredmetaDTO> dto= slusanjePredmetaRepository.findByPredmetId(id).stream().map(d->new SlusanjePredmetaDTO(d)).toList();
        return dto;
    }
    public List<PredmetDTO> getPredmetiStudenta(long id){
        List<SlusanjePredmeta> s= slusanjePredmetaRepository.findAllByStudentId(id);
        List<Long> ids= new ArrayList<>();
        for(SlusanjePredmeta x:s){
            ids.add(x.getPredmetId());
        }
        List<PredmetDTO> d =adminClient.getPredmetiByIds(ids).getBody() ;
        return d;
    }
    public SlusanjePredmetaDTO upisiStudeenta(List<SlusanjaReqDto> dto){
        for( SlusanjaReqDto req:dto){
            List<SlusanjePredmeta> list = new ArrayList<>();
            Student s = studentRepository.findById(req.getStudentId()).orElse(null);
            for (Long id:req.getPredmetIds()){
                list.add(new SlusanjePredmeta(null,id,s));
            }
            slusanjePredmetaRepository.deleteAllByStudentId(s.getId());
            slusanjePredmetaRepository.saveAll(list);
        }
        return null;
    }
    public void delete(long studentId,long predmetId){
        slusanjePredmetaRepository.deleteByStudentIdAndPredmetId(studentId,predmetId);
    }
}
