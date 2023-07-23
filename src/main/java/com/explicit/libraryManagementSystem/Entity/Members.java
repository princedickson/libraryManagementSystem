package com.explicit.libraryManagementSystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enter first name")
    @NotEmpty(message = "Enter first name")
    private String FirstName;

    @NotNull(message = "Enter last name")
    @NotEmpty(message = "Enter last name")
    private String LastName;

    @NotNull(message = "Enter email")
    @NotEmpty(message = "Enter email")
    private String Email;

    private String Contact;
    private String Gender;

    private String MemberType;

    public Members(@NotNull(message = "Enter first name") String firstName,
                   @NotNull(message = "Enter last name") String lastName,
                   @NotNull(message = "Enter email") String email, String contact,
                   String gender, String memberType) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Contact = contact;
        Gender = gender;
        MemberType = memberType;
    }

    public Members() {
    }
}
