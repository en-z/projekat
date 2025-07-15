package com.projekat.admin_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private int esbp;
    private Boolean aktivan;
    private int semestar;
    private int dan;
    @ManyToOne
    private StudijskiProgram studijskiProgram;
}
