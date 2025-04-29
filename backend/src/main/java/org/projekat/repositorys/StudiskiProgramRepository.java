package org.projekat.repositorys;

import org.projekat.model.StudiskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiskiProgramRepository extends JpaRepository<StudiskiProgram,Long> {
}
