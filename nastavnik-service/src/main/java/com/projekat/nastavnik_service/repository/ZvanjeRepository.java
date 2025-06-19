package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Angazovanja;
import com.projekat.nastavnik_service.entity.Zvanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ZvanjeRepository extends JpaRepository<Zvanje,Long> {
    List<Zvanje> findAllByNastavnikId(Long id);
}
