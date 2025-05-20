package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.users.Nastavnik;
import org.projekat.model.users.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zavrsniRad")
public class ZavrsniRad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String opis;
    private String naslov;
    private String status;
    @ManyToOne
    @JoinColumn(name = "nastavnik_id",referencedColumnName = "id")
    private Nastavnik mentor;
    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;
}
