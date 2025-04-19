package org.projekat.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_idosoba", referencedColumnName = "idosoba"),
            inverseJoinColumns = @JoinColumn(name = "roles_idroles", referencedColumnName = "idroles")
    )
    private Set<Role> roles;
}
