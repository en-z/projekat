package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles",joinColumns=@JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private List<String> roles;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Osoba osoba;
}
