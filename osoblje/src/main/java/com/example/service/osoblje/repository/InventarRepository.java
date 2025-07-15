package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Inventar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarRepository extends JpaRepository<Inventar,Long> {
    List<Inventar> findByFakultetId(Long fakultetId);
}
