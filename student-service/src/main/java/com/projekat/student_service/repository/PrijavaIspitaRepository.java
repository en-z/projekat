package com.projekat.student_service.repository;

import com.projekat.student_service.entity.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita,Long> {

}
