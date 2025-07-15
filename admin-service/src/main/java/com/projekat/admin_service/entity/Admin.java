package com.projekat.admin_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    private String ime;
    private String prezime;
    private Boolean aktivan;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "adresa_id")
    private Adresa adresa;
}
