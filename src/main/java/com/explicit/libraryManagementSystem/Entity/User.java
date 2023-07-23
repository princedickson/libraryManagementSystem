package com.explicit.libraryManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // to DO perform validation
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User() {
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
    @JoinTable(name = "user_id", joinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id",
            referencedColumnName = "id"))
    private Collection<Roles> role;

    public User(String firstName, String lastName, String email, String password, List<Roles> userRole) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.role = userRole;
    }
}
