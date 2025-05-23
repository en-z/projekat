package org.projekat.repositorys;

import org.projekat.model.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet,Long> {
    List<Fakultet> findByUniverzitet_Id(long id);
}
