package com.projekat.auth_service.repository;

import com.projekat.auth_service.entity.ZaUpis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZaUpisRepostiory extends JpaRepository<ZaUpis,Long> {
    Page<ZaUpis> findByFakultetId(Long id, Pageable pageable);
    ZaUpis findByUserId(Long id);
}
