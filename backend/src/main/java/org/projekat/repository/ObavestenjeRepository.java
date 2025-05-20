package org.projekat.repository;

import org.projekat.model.Obavestenje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Long> {
}
