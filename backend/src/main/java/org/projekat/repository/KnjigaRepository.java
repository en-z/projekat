package org.projekat.repository;

import lombok.Getter;
import org.projekat.model.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga,Long> {
    List<Knjiga> findByKategorijaIgnoreCase(String kategorija);
    @Query("SELECT k FROM Knjiga k WHERE " +
            "LOWER(k.naziv) LIKE LOWER(CONCAT('%', :naziv, '%')) OR " +
            "LOWER(k.kategorija) LIKE LOWER(CONCAT('%', :kategorija, '%')) OR " +
            "LOWER(k.opis) LIKE LOWER(CONCAT('%', :opis, '%')) OR " +
            "LOWER(k.autor) LIKE LOWER(CONCAT('%', :autor, '%'))")
    List<Knjiga> search(@Param("naziv") String naziv,@Param("kategorija") String kategorija,@Param("opis") String opis,@Param("autor") String autor);
}
