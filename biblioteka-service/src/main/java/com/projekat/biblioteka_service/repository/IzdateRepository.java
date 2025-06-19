package com.projekat.biblioteka_service.repository;

import com.projekat.biblioteka_service.entity.Izdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface IzdateRepository extends JpaRepository<Izdate,Long> {
    List<Izdate> findAllByUserId(long id);
    List<Izdate> findByDatumVracanja(LocalDate datum);
    List<Izdate> findByDatumVracanjaIsNull();

}
