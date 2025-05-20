package org.projekat.model;

import jakarta.persistence.*;
import lombok.*;
import org.projekat.model.users.Student;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "upis_godine")
public class UpisGodine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int godinaStudija;

    private Date datumUpisa;

    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
