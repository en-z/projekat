package com.projekat.admin_service.repository;

import com.projekat.admin_service.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUserId(Long id);
    void deleteByUserId(Long id);
}
