package org.projekat.repository;

import org.projekat.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudijskiProgramRepository extends JpaRepository<StudijskiProgram,Long> {
    List<StudijskiProgram> findByFakultet_Id(Long id);
}
