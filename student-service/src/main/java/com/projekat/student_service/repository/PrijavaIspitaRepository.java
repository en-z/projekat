package com.projekat.student_service.repository;

import com.projekat.student_service.entity.PrijavaIspita;
import com.projekat.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita,Long> {
    List<PrijavaIspita> findByStudentId(long id);
    @Query("SELECT p.student FROM PrijavaIspita p " +
            "WHERE p.rok.id = :rokId AND p.predmetId = :predmetId")
    List<Student> findStudentiByRokIdAndPredmetId(
            @Param("rokId") Long rokId,
            @Param("predmetId") Long predmetId
    );

}
