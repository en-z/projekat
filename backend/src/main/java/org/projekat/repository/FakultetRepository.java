package org.projekat.repository;

import org.projekat.model.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet,Long> {
}
