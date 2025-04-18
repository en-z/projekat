package org.projekat.repositorys;

import org.projekat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { //Za brisanje i dodavanje u batchu
    Optional<User> findByUsernameOrEmail(String email, String username);
}
