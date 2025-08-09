package com.projekat.student_service.repository;

import com.projekat.student_service.entity.SlusanjePredmeta;
import com.projekat.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlusanjePredmetaRepository extends JpaRepository<SlusanjePredmeta,Long> {
    List<SlusanjePredmeta> findByPredmetId(long id);
    List<SlusanjePredmeta> findAllByStudentId(long id);

    @Query("SELECT s.student FROM SlusanjePredmeta s WHERE s.predmetId = :predmetId")
    List<Student> findStudentiZaPredmet(@Param("predmetId") Long predmetId);

    void deleteByStudentIdAndPredmetId(Long studentId, Long predmetId);
    void deleteAllByStudentId(Long studentId);
}
