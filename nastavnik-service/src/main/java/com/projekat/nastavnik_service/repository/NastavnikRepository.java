package com.projekat.nastavnik_service.repository;

import com.projekat.nastavnik_service.entity.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik,Long> {
    Optional<Nastavnik> findByUserId(long id);
    List<Nastavnik> findByZvanjeIsNull();
    @Query("""
SELECT n FROM Nastavnik n
WHERE (:ime IS NULL OR n.ime LIKE %:ime%)
  AND (:prezime IS NULL OR n.prezime LIKE %:prezime%)
  AND (:email IS NULL OR n.email LIKE %:email%)
""")
    List<Nastavnik> search(
            @Param("ime") String ime,
            @Param("prezime") String prezime,
            @Param("email") String email
    );
}
