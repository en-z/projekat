package org.projekat.service.users;

import org.projekat.model.Student;
import org.projekat.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceDusan {

    private final StudentRepository repository;

    public StudentServiceDusan(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Student> search(String ime, String prezime, String brojIndeksa,
                                Integer godinaUpisa, Double minProsek, Double maxProsek) {
        return repository.search(ime, prezime, brojIndeksa, godinaUpisa, minProsek, maxProsek);
    }
}
