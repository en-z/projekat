package org.projekat.repository.users;

import org.projekat.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s " +
            "WHERE (:ime IS NULL OR LOWER(s.osoba.ime) LIKE LOWER(CONCAT('%', :ime, '%'))) " +
            "AND (:prezime IS NULL OR LOWER(s.osoba.prezime) LIKE LOWER(CONCAT('%', :prezime, '%'))) " +
            "AND (:brojIndeksa IS NULL OR s.brojIndeksa LIKE CONCAT('%', :brojIndeksa, '%')) " +
            "AND (:godinaUpisa IS NULL OR s.godinaUpisa = :godinaUpisa) " +
            "AND (:minProsek IS NULL OR s.prosecnaOcena >= :minProsek) " +
            "AND (:maxProsek IS NULL OR s.prosecnaOcena <= :maxProsek)")
    List<Student> search(
            @Param("ime") String ime,
            @Param("prezime") String prezime,
            @Param("brojIndeksa") String brojIndeksa,
            @Param("godinaUpisa") Integer godinaUpisa,
            @Param("minProsek") Double minProsek,
            @Param("maxProsek") Double maxProsek
    );


}
