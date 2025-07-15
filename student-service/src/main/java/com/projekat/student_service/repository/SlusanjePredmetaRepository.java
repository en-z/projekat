package com.projekat.student_service.repository;

import com.projekat.student_service.entity.SlusanjePredmeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlusanjePredmetaRepository extends JpaRepository<SlusanjePredmeta,Long> {
    List<SlusanjePredmeta> findByPredmetId(long id);
    List<SlusanjePredmeta> findAllByStudentId(long id);
    void deleteByStudentIdAndPredmetId(Long studentId, Long predmetId);
}
