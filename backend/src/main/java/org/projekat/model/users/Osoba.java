package org.projekat.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import org.projekat.model.Adresa;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "osoba")
@Getter
@Setter
public class Osoba {
    @Id
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private String ime;

    private String prezime;

    @ManyToOne
    @JoinColumn(name = "adresa_id",referencedColumnName = "id")
    private Adresa adresa;
}
