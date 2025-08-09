package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.KolokvijumRezultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KolokvijumiRezultatRepository extends JpaRepository<KolokvijumRezultat,Long> {
    List<KolokvijumRezultat> findAllByStudentId(Long id);
    List<KolokvijumRezultat> findAllByKolokvijumId(Long id);
    List<KolokvijumRezultat> findAllByPredmetId(Long id);
    List<KolokvijumRezultat> findAllByPredmetIdAndStudentId(Long predmetId,Long studentId);
}
