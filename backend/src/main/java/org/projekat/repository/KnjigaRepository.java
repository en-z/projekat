package org.projekat.repository.biblioteka;

import lombok.Getter;
import org.projekat.model.biblioteka.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga,Long> {
    List<Knjiga> findByKategorijaIgnoreCase(String kategorija);
}
