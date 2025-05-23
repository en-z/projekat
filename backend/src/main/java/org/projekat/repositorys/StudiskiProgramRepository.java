package org.projekat.repositorys;

import org.projekat.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudiskiProgramRepository extends JpaRepository<StudijskiProgram,Long> {
    List<StudijskiProgram> findByFakultet_Id(Long id);
}
