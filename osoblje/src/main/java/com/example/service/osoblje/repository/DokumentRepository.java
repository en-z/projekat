package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument,Long> {
    List<Dokument> findByNastavnikId(Long id);
    List<Dokument> findByStudentId(Long id);
}
