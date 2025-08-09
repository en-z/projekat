package com.projekat.biblioteka_service.DTO;

import com.projekat.biblioteka_service.entity.Knjiga;
import com.projekat.biblioteka_service.entity.Zahtjev;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ZahtjevSearchSpecifikacija {
    public static Specification<Zahtjev> getSpecification(ZahtjevSearch criteria) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (criteria.getUserIdMin() != null) {
                p = cb.and(p, cb.ge(root.get("userId"), criteria.getUserIdMin()));
            }
            if (criteria.getUserIdMax() != null) {
                p = cb.and(p, cb.le(root.get("userId"), criteria.getUserIdMax()));
            }

            if (criteria.getImeContains() != null && !criteria.getImeContains().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(root.get("ime")), "%" + criteria.getImeContains().toLowerCase() + "%"));
            }

            if (criteria.getPrezimeContains() != null && !criteria.getPrezimeContains().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(root.get("prezime")), "%" + criteria.getPrezimeContains().toLowerCase() + "%"));
            }

            Join<Zahtjev, Knjiga> knjigaJoin = root.join("knjiga", JoinType.LEFT);

            if (criteria.getKnjigaNazivContains() != null && !criteria.getKnjigaNazivContains().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(knjigaJoin.get("naziv")), "%" + criteria.getKnjigaNazivContains().toLowerCase() + "%"));
            }

            if (criteria.getKnjigaKategorijaContains() != null && !criteria.getKnjigaKategorijaContains().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(knjigaJoin.get("kategorija")), "%" + criteria.getKnjigaKategorijaContains().toLowerCase() + "%"));
            }

            if (criteria.getKnjigaOpisContains() != null && !criteria.getKnjigaOpisContains().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(knjigaJoin.get("opis")), "%" + criteria.getKnjigaOpisContains().toLowerCase() + "%"));
            }

            if (criteria.getKnjigaGodinaIzdavanjaMin() != null) {
                p = cb.and(p, cb.ge(knjigaJoin.get("godinaIzdavanja"), criteria.getKnjigaGodinaIzdavanjaMin()));
            }
            if (criteria.getKnjigaGodinaIzdavanjaMax() != null) {
                p = cb.and(p, cb.le(knjigaJoin.get("godinaIzdavanja"), criteria.getKnjigaGodinaIzdavanjaMax()));
            }

            if (criteria.getKnjigaAutorContains() != null && !criteria.getKnjigaAutorContains().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(knjigaJoin.get("autor")), "%" + criteria.getKnjigaAutorContains().toLowerCase() + "%"));
            }

            if (criteria.getKnjigaKolicinaMin() != null) {
                p = cb.and(p, cb.ge(knjigaJoin.get("kolicina"), criteria.getKnjigaKolicinaMin()));
            }
            if (criteria.getKnjigaKolicinaMax() != null) {
                p = cb.and(p, cb.le(knjigaJoin.get("kolicina"), criteria.getKnjigaKolicinaMax()));
            }

            return p;
        };
    }
}
