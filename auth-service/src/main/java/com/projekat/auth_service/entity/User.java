package com.projekat.auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String ime;
    private String prezime;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles",joinColumns=@JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private List<String> roles;
}
