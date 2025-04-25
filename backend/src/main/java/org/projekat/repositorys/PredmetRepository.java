package org.projekat.repositorys;

import org.projekat.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredmetRepository extends JpaRepository<Predmet,Long> {
}
