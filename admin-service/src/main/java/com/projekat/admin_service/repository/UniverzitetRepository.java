package com.projekat.admin_service.repository;

import com.projekat.admin_service.entity.Univerzitet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniverzitetRepository extends JpaRepository<Univerzitet,Long> {
}
