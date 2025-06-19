package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.InstrumentEvaluacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentEvaluacijeRepository extends JpaRepository<InstrumentEvaluacije,Long> {
}
