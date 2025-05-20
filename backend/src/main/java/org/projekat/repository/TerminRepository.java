package org.projekat.repository;

import org.projekat.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    List<Termin> findByPredmetId(Long predmetId);
}
