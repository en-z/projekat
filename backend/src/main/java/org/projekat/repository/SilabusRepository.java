package org.projekat.repository;

import org.projekat.model.Silabus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SilabusRepository extends JpaRepository<Silabus, Long> {
    Optional<Silabus> findByPredmetId(Long predmetId);
}
