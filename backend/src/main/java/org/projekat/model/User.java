package org.projekat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    @ElementCollection
    @CollectionTable(name = "users_roles",joinColumns=@JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;
}
