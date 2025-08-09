package com.projekat.biblioteka_service.repository;

import com.projekat.biblioteka_service.entity.Zahtjev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtjevRepository extends JpaRepository<Zahtjev,Long> , JpaSpecificationExecutor<Zahtjev> {

}
