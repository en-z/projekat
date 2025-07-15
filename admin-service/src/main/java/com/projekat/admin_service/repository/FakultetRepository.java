package com.projekat.admin_service.repository;

import com.projekat.admin_service.entity.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet,Long> {
    List<Fakultet> findByUniverzitet_IdAndAktivanTrue(long id);
    List<Fakultet> findByAktivanFalse();
    List<Fakultet> findByAktivanTrue();
}
