package com.projekat.admin_service.repository;

import com.projekat.admin_service.entity.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet,Long> {
    List<Predmet> findByStudijskiProgram_Id(long id);
    List<Predmet> findByNazivContainingIgnoreCase(String naziv);
    /*
    @Query(value = """
        SELECT * FROM predmet p
        WHERE p.semestar <= :maxSemestar
        AND p.studijski_program_id = :id
        AND p.id NOT IN (
            SELECT ii.predmet_id from IshodIspita ii
            where ii.student_id= :uId and ii.bodovi < 51
        )
        """,nativeQuery = true)
    List<Predmet> findByNijePolozen(@Param("uId") long userId,@Param("id") long programId,@Param("maxSemestar")int maxSemestar);
*/
    List<Predmet> findAllById(Iterable<Long> ids);

}
