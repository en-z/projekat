package org.projekat.repositorys;

import org.projekat.model.IspitPrijava;
import org.projekat.model.StudentPredmetId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IspitPrijavaRepository extends JpaRepository<IspitPrijava, StudentPredmetId> {
    List<IspitPrijava> findByStudentId(Long id);
    List<IspitPrijava> findByPredmetId(Long id);
}
