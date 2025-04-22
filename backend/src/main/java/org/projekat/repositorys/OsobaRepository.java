package org.projekat.repositorys;

import org.projekat.model.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsobaRepository extends JpaRepository<Osoba, Long> {
}
