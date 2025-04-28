package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Drzava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iddrzava;
    private String ime;
}
