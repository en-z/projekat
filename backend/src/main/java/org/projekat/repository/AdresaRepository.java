package org.projekat.repository;

import org.projekat.model.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa,Long> {
    Optional<Adresa> findByUlicaAndBrojAndGradAndDrzava(String ulica, String broj,String grad,String drzava);
}
