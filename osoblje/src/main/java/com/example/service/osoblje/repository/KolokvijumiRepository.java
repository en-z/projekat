package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Kolokvijumi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KolokvijumiRepository extends JpaRepository<Kolokvijumi,Long> {
    List<Kolokvijumi> findByProgramId(Long programId);

    List<Kolokvijumi> findByPredmetId(Long predmetId);
}
