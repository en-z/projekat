package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Osoblje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OsobljeRepository extends JpaRepository<Osoblje,Long> {
    Optional<Osoblje> findByUserId(Long userId);
}
