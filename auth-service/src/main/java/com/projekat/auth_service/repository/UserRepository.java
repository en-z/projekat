package com.projekat.auth_service.repository;

import com.projekat.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { //Za brisanje i dodavanje u batchu
    Optional<User> findByEmail(String email);
    List<User> findByEmailContainingIgnoreCase(String email);
    List<User> findByRolesContaining(String role);
    boolean existsByEmail(String email);
}
