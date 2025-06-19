package com.projekat.auth_service.repository;

import com.projekat.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { //Za brisanje i dodavanje u batchu
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
