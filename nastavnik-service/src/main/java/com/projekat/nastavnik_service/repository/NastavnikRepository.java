package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik,Long> {
    Optional<Nastavnik> findByUserId(long id);
}
