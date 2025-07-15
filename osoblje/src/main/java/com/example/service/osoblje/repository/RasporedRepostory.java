package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Raspored;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RasporedRepostory extends JpaRepository<Raspored,Long> {
    List<Raspored>findByProgramId(long programId);
}
