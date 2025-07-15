package com.projekat.admin_service.repository;

import com.projekat.admin_service.entity.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudijskiProgramRepository extends JpaRepository<StudijskiProgram,Long> {
    List<StudijskiProgram> findByFakultet_IdAndAktivanTrue(Long id);
    List<StudijskiProgram> findByAktivanFalse();
    List<StudijskiProgram> findByAktivanTrue();
}
