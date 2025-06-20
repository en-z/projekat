package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.ZavrsniRad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZavrsniRadRepository extends JpaRepository<ZavrsniRad,Long> {
    List<ZavrsniRad> findByNastavnikUserId(long userId);
    List<ZavrsniRad> findByNastavnikId(long userId);
}
