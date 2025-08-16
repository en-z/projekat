package com.projekat.biblioteka_service.repository;

import com.projekat.biblioteka_service.entity.Izdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface IzdateRepository extends JpaRepository<Izdate,Long> {
    List<Izdate> findAllByUserId(long id);
    List<Izdate> findByDatumVracanja(LocalDate datum);
    List<Izdate> findByTrajnaFalse();
    @Query("""
        select i from Izdate i
        where 
          (:userIdMin is null or i.userId >= :userIdMin) and
          (:userIdMax is null or i.userId <= :userIdMax) and
          (:ime is null or i.ime<= :ime) and
          (:prezime is null or i.prezime<= :prezime) and
          (:knjigaNazivContains is null or lower(i.knjiga.naziv) like lower(concat('%', :knjigaNazivContains, '%'))) and
          (:knjigaKategorijaContains is null or lower(i.knjiga.kategorija) like lower(concat('%', :knjigaKategorijaContains, '%'))) and
          (:knjigaOpisContains is null or lower(i.knjiga.opis) like lower(concat('%', :knjigaOpisContains, '%'))) and
          (:knjigaGodinaIzdavanjaMin is null or i.knjiga.godinaIzdavanja >= :knjigaGodinaIzdavanjaMin) and
          (:knjigaGodinaIzdavanjaMax is null or i.knjiga.godinaIzdavanja <= :knjigaGodinaIzdavanjaMax) and
          (:knjigaAutorContains is null or lower(i.knjiga.autor) like lower(concat('%', :knjigaAutorContains, '%'))) and
          (:knjigaKolicinaMin is null or i.knjiga.kolicina >= :knjigaKolicinaMin) and
          (:knjigaKolicinaMax is null or i.knjiga.kolicina <= :knjigaKolicinaMax) and
          (:trajna is null or i.trajna = :trajna)
    """)
    List<Izdate> search(
            @Param("userIdMin") Long userIdMin,
            @Param("userIdMax") Long userIdMax,
            @Param("ime") String ime,
            @Param("prezime") String prezime,
            @Param("knjigaNazivContains") String knjigaNazivContains,
            @Param("knjigaKategorijaContains") String knjigaKategorijaContains,
            @Param("knjigaOpisContains") String knjigaOpisContains,
            @Param("knjigaGodinaIzdavanjaMin") Integer knjigaGodinaIzdavanjaMin,
            @Param("knjigaGodinaIzdavanjaMax") Integer knjigaGodinaIzdavanjaMax,
            @Param("knjigaAutorContains") String knjigaAutorContains,
            @Param("knjigaKolicinaMin") Integer knjigaKolicinaMin,
            @Param("knjigaKolicinaMax") Integer knjigaKolicinaMax,
            @Param("trajna") Boolean trajna
    );
}
