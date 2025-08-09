package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.client.StudentClient;
import com.projekat.nastavnik_service.dto.StudentOcenaDTO;
import com.projekat.nastavnik_service.entity.IshodIspita;
import com.projekat.nastavnik_service.repository.IshodIspitaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class IshodIspitaService {
    @Autowired
    private StudentClient studentClient;
    private final IshodIspitaRepository repository;

    public IshodIspitaService(IshodIspitaRepository repository) {
        this.repository = repository;
    }

    public List<IshodIspita> findAll() {
        return repository.findAll();
    }

    public List<IshodIspita> findByStudent(long id) {
        return repository.findAllByStudentId(id);
    }
    public Optional<IshodIspita> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<IshodIspita> save(List<IshodIspita> ishodi) {
        List<IshodIspita> saved = new ArrayList<>();
        List<StudentOcenaDTO> oceneZaUpis = new ArrayList<>();
        Long predmetId =0L;
        for (IshodIspita ishod : ishodi) {
            predmetId = ishod.getPredmetId();
            List<IshodIspita> prethodni = repository.findAllByStudentIdAndPredmetId(ishod.getStudentId(), ishod.getPredmetId());
            int brojPokusaja = (prethodni == null || prethodni.isEmpty()) ? 1 : prethodni.size() + 1;
            ishod.setBrojPokusaja(brojPokusaja);

            repository.save(ishod);

            if (ishod.getPolozen()) {
                List<IshodIspita> svi = repository.findAllByStudentId(ishod.getStudentId());
                float ocena = izracunajProsecnuOcenu(svi);
                oceneZaUpis.add(new StudentOcenaDTO(ishod.getStudentId(), ocena));
            }

            saved.add(ishod);
        }
        if (!oceneZaUpis.isEmpty()){
            studentClient.upisiOcenu(predmetId,oceneZaUpis);
        }
        return saved;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean canEnterGrade(LocalDateTime datumTermina) {
        return datumTermina.plusDays(15).isAfter(LocalDateTime.now());
    }
    public static float izracunajProsecnuOcenu(List<IshodIspita> ishodi) {
        List<Integer> ocene = ishodi.stream()
                .filter(ishod -> ishod.getBodovi() > 50)
                .map(ishod -> bodoviToOcena(ishod.getBodovi()))
                .toList();

        if (ocene.isEmpty()) return 0.0F;

        float suma = ocene.stream().mapToInt(Integer::intValue).sum();
        return suma / ocene.size();
    }


    private static int bodoviToOcena(float bodovi) {
        if (bodovi >= 91) return 10;
        if (bodovi >= 81) return 9;
        if (bodovi >= 71) return 8;
        if (bodovi >= 61) return 7;
        if (bodovi >= 51) return 6;
        return 5;
    }

}
