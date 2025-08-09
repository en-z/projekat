package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Dokument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument,Long> {
    Page<Dokument> findByUserId(Long id, Pageable pageable);

    Page<Dokument>findAll(Pageable pageable);
}
