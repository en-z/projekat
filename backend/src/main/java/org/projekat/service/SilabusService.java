package org.projekat.service;

import lombok.RequiredArgsConstructor;
import org.projekat.model.Nastavnik;
import org.projekat.model.Predmet;
import org.projekat.model.Silabus;
import org.projekat.repository.PredmetRepository;
import org.projekat.repository.SilabusRepository;
import org.projekat.repository.NastavnikRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SilabusService {

    private final SilabusRepository silabusRepository;
    private final PredmetRepository predmetRepository;
    private final NastavnikRepository nastavnikRepository;

    public Optional<Silabus> findById(Long id) {
        return silabusRepository.findById(id);
    }

    public Optional<Silabus> findByPredmetId(Long predmetId) {
        return silabusRepository.findByPredmetId(predmetId);
    }

    public Silabus save(Silabus silabus) {
        return silabusRepository.save(silabus);
    }

    public Silabus create(Long predmetId, Long nastavnikId, String sadrzaj) {
        if (silabusRepository.findByPredmetId(predmetId).isPresent()) {
            throw new RuntimeException("Silabus za ovaj predmet već postoji");
        }

        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));

        Nastavnik nastavnik = nastavnikRepository.findById(nastavnikId)
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));

        Silabus silabus = new Silabus();
        silabus.setPredmet(predmet);
        silabus.setAutor(nastavnik);
        silabus.setSadrzaj(sadrzaj);

        return silabusRepository.save(silabus);
    }

    public Silabus update(Long silabusId, String noviSadrzaj) {
        Silabus silabus = silabusRepository.findById(silabusId)
                .orElseThrow(() -> new RuntimeException("Silabus ne postoji"));

        silabus.setSadrzaj(noviSadrzaj);
        return silabusRepository.save(silabus);
    }

    public void deleteById(Long id) {
        silabusRepository.deleteById(id);
    }


}
