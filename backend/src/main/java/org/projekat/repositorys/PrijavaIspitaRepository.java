package org.projekat.repositorys;

import org.projekat.model.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita,Long> {

}
