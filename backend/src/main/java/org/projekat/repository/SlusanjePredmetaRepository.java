package org.projekat.repository;

import org.projekat.model.SlusanjePredmeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlusanjePredmetaRepository extends JpaRepository<SlusanjePredmeta, Long> {
    List<SlusanjePredmeta> findByPredmetId(Long predmetId);

    List<SlusanjePredmeta> findByStudent_Osoba_User_Id(Long studentId);
}
