package org.projekat.repositorys;

import org.projekat.model.IshodIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IshodIspitaRepository extends JpaRepository<IshodIspita,Long> {
}
