package org.projekat.repositorys;

import org.projekat.model.InstrumentEvaluacije;
import org.projekat.model.StudiskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentEvaluacijeRepository extends JpaRepository<InstrumentEvaluacije,Long> {
    List<InstrumentEvaluacije> findByStudiskiProgram(StudiskiProgram studiskiProgram);
}
