package com.projekat.student_service.repository;

import com.projekat.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s " +
            "WHERE (:ime IS NULL OR LOWER(s.ime) LIKE LOWER(CONCAT('%', :ime, '%'))) " +
            "AND (:prezime IS NULL OR LOWER(s.prezime) LIKE LOWER(CONCAT('%', :prezime, '%'))) " +
            "AND (:brojIndeksa IS NULL OR s.brojIndeksa LIKE CONCAT('%', :brojIndeksa, '%')) " +
            "AND (:godinaUpisa IS NULL OR s.godinaUpisa = :godinaUpisa) " +
            "AND (:minProsek IS NULL OR s.prosecnaOcena >= :minProsek) " +
            "AND (:maxProsek IS NULL OR s.prosecnaOcena <= :maxProsek)")
    List<Student> search(
            @Param("ime") String ime,
            @Param("prezime") String prezime,
            @Param("brojIndeksa") String brojIndeksa,
            @Param("godinaUpisa") Integer godinaUpisa,
            @Param("minProsek") Float minProsek,
            @Param("maxProsek") Float maxProsek
    );
    Optional<Student> findByUserId(long id);
}
