package com.projekat.biblioteka_service.repository;

import com.projekat.biblioteka_service.entity.Notifikacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotifikacijeRepo extends JpaRepository<Notifikacija,Long> {
    List<Notifikacija> findByUserId(long id);
    List<Notifikacija> findByIzdateId(long id);
    void deleteByIzdateId(long id);
}
