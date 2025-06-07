package org.projekat.repository;

import org.projekat.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet,Long> {
    List<Predmet> findByStudijskiProgram_Id(long id);

    @Query(value = """
        SELECT * FROM predmet p
        WHERE p.semestar <= :maxSemestar
        AND p.studijski_program_id = :id
        AND p.id NOT IN ( 
            SELECT pi.predmet_id from prijavaIspita pi
            WHERE pi.student_id= :uId
        )
        AND p.id NOT IN (
            SELECT ii.predmet_id from IshodIspita ii
            where ii.student_id= :uId and ii.bodovi < 51
        )
        """, nativeQuery = true)
    List<Predmet> findNijePrijavljenIliPolozen(
            @Param("maxSemestar") int maxSemestar,
            @Param("uId") long userId,
            @Param("id") long studiskiId);

    List<Predmet> findByNazivContainingIgnoreCase(String naziv);
}
