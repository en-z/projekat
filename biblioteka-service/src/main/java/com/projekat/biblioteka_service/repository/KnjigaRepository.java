package com.projekat.biblioteka_service.repository;

import com.projekat.biblioteka_service.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga,Long> {
    @Query("SELECT k FROM Knjiga k WHERE " +
            "(:naziv IS NULL OR LOWER(k.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))) AND " +
            "(:kategorija IS NULL OR LOWER(k.kategorija) LIKE LOWER(CONCAT('%', :kategorija, '%'))) AND " +
            "(:opis IS NULL OR LOWER(k.opis) LIKE LOWER(CONCAT('%', :opis, '%'))) AND " +
            "(:autor IS NULL OR LOWER(k.autor) LIKE LOWER(CONCAT('%', :autor, '%'))) AND " +
            "(:godinaOd IS NULL OR k.godinaIzdavanja >= :godinaOd) AND " +
            "(:godinaDo IS NULL OR k.godinaIzdavanja <= :godinaDo) AND" +
            "(:kolicinaOd IS NULL OR k.kolicina>= :kolicinaOd) AND" +
            "(:kolicinaDo IS NULL OR k.kolicina<= :kolicinaDo)"
    )
    List<Knjiga> search(@Param("naziv") String naziv,@Param("kategorija") String kategorija,@Param("opis") String opis,@Param("autor") String autor,@Param("godinaOd") Integer godinaOd,@Param("godinaDo") Integer godinaDo,@Param("kolicinaOd") Integer kolicinaOd,@Param("kolicinaDo") Integer kolicinaDo);
}
