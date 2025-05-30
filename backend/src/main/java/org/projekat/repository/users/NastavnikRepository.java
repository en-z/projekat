package org.projekat.repository.users;

import org.projekat.model.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik,Long> {
}
