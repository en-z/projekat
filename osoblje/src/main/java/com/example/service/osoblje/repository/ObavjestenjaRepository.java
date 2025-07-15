package com.example.service.osoblje.repository;

import com.example.service.osoblje.models.Obavjestenja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObavjestenjaRepository extends JpaRepository<Obavjestenja,Long> {
    List<Obavjestenja> findByFakultetID(Long fakultetID);
    List<Obavjestenja> findTop10ByFakultetIDOrderByGodinaDescMesecDescDanDesc(Long fakultetID);
    @Query("""
    SELECT o FROM Obavjestenja o
    WHERE o.fakultetID = :fakultetID
    AND (:mesec IS NULL OR o.mesec = :mesec)
    AND (:godina IS NULL OR o.godina = :godina)
""")
    List<Obavjestenja> findByFakultetIDAndMesecAndGodina(
            @Param("fakultetID") Long fakultetID,
            @Param("mesec") Integer mesec,
            @Param("godina") Integer godina
    );

}
