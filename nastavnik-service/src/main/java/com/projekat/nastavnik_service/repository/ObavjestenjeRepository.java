package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Obavestenje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObavjestenjeRepository extends JpaRepository<Obavestenje,Long> {
    List<Obavestenje> findByPredmetId(long id);
}