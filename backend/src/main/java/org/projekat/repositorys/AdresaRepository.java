package org.projekat.repositorys;

import org.projekat.model.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa,Long> {
}
