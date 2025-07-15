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
    Optional<Student> findByUserId(long id);
    @Query("""
    SELECT s FROM Student s
    JOIN s.adresa a
    WHERE (:ime IS NULL OR LOWER(s.ime) LIKE LOWER(CONCAT('%', :ime, '%')))
      AND (:prezime IS NULL OR LOWER(s.prezime) LIKE LOWER(CONCAT('%', :prezime, '%')))
      AND (:brojIndeksa IS NULL OR s.brojIndeksa = :brojIndeksa)
      AND (:godinaUpisa IS NULL OR s.godinaUpisa = :godinaUpisa)
      AND (:godinaStudija IS NULL OR s.godinaStudija = :godinaStudija)
      AND (:studiskiId IS NULL OR s.studiskiId = :studiskiId)
      AND (:prosekMin IS NULL OR s.prosecnaOcena >= :prosekMin)
      AND (:prosekMax IS NULL OR s.prosecnaOcena <= :prosekMax)
      AND (:esbpMin IS NULL OR s.osvojeniEsbp >= :esbpMin)
      AND (:esbpMax IS NULL OR s.osvojeniEsbp <= :esbpMax)
      AND (:aktivan IS NULL OR s.aktivan = :aktivan)
      AND (:ulica IS NULL OR LOWER(a.ulica) LIKE LOWER(CONCAT('%', :ulica, '%')))
      AND (:broj IS NULL OR a.broj = :broj)
      AND (:grad IS NULL OR LOWER(a.grad) LIKE LOWER(CONCAT('%', :grad, '%')))
      AND (:drzava IS NULL OR LOWER(a.drzava) LIKE LOWER(CONCAT('%', :drzava, '%')))
""")
    List<Student> search(
            @Param("ime") String ime,
            @Param("prezime") String prezime,
            @Param("brojIndeksa") String brojIndeksa,
            @Param("godinaUpisa") Integer godinaUpisa,
            @Param("godinaStudija") Integer godinaStudija,
            @Param("studiskiId") Long studiskiId,
            @Param("prosekMin") Float prosekMin,
            @Param("prosekMax") Float prosekMax,
            @Param("esbpMin") Integer esbpMin,
            @Param("esbpMax") Integer esbpMax,
            @Param("aktivan") Boolean aktivan,
            @Param("ulica") String ulica,
            @Param("broj") String broj,
            @Param("grad") String grad,
            @Param("drzava") String drzava
    );

}
