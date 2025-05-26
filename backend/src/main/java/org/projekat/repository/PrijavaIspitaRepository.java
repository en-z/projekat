package org.projekat.repository;

import org.projekat.model.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita,Long> {

    List<PrijavaIspita> findByPredmetId(Long predmetId);
}
