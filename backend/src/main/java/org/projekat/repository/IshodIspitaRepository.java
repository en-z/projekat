package org.projekat.repository;

import org.projekat.model.IshodIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IshodIspitaRepository extends JpaRepository<IshodIspita,Long> {
    List<IshodIspita> findAllByStudent_Osoba_User_id(long id);
}
