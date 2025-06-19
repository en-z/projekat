package com.projekat.student_service.repository;

import com.projekat.student_service.entity.IspitniRok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IspitniRokRepository extends JpaRepository<IspitniRok,Long> {
    @Query(value = "SELECT * FROM IspitniRok ir WHERE CURRENT_DATE BETWEEN DATE_SUB(ir.pocetak, INTERVAL 10 DAY) AND ir.kraj", nativeQuery = true)
    List<IspitniRok> getAktivneRokove();
}
