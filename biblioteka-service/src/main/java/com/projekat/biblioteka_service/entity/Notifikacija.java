package com.projekat.biblioteka_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifikacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String poruka;
    @OneToOne
    private Izdate izdate;

    public Notifikacija(Izdate iz){
        this.userId = iz.getUserId();
        this.izdate= iz;
    }
}
