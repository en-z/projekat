package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Angazovanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AngazovanjaRepository extends JpaRepository<Angazovanja,Long> {
    List<Angazovanja> findAllByNastavnikId(long id);
}
