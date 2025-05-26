package org.projekat.repository;

import org.projekat.model.Angazovanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AngazovanjeRepository extends JpaRepository<Angazovanje, Long> {

    @Query("SELECT a FROM Angazovanje a WHERE a.nastavnik.osoba.user.id = :userId")
    List<Angazovanje> findByUserId(Long userId);

    void deleteByNastavnikId(Long id);

}
