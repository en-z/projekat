package org.projekat.repository;

import org.projekat.model.IspitniRok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IspitniRokRepository extends JpaRepository<IspitniRok,Long> {
    @Query(value = "SELECT * FROM IspitniRok ir WHERE CURRENT_DATE BETWEEN DATE_SUB(ir.pocetak, INTERVAL 10 DAY) AND ir.kraj", nativeQuery = true)
    List<IspitniRok> getAktivneRokove();
}
