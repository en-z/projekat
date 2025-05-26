package org.projekat.repository;

import org.projekat.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
    List<Predmet> findByNazivContainingIgnoreCase(String naziv);
}
