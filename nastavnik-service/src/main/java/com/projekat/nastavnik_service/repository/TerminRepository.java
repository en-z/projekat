package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TerminRepository extends JpaRepository<Termin,Long> {
    List<Termin> findAllByPredmetId(long id);
}
