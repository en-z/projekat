package com.projekat.student_service.repository;

import com.projekat.student_service.entity.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita,Long> {
    List<PrijavaIspita> findByStudentId(long id);
}
