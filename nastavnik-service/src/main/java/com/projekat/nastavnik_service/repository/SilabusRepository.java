package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Silabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SilabusRepository extends JpaRepository<Silabus,Long> {
    List<Silabus> findAllByPredmetId(long id);
    Optional<Silabus> findByPredmetId(long id);
}
