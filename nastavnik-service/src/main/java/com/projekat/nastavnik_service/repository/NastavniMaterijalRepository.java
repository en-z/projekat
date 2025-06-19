package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.NastavniMaterijal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NastavniMaterijalRepository extends JpaRepository<NastavniMaterijal,Long> {
   List<NastavniMaterijal> findAllByPredmetId(long predmetId);
}
