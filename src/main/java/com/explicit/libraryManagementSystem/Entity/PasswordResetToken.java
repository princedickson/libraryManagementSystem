package com.explicit.libraryManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken extends PasswordReset{
    private static final int EXPIRATION_TIME = 10 ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,
            foreignKey = @ForeignKey (name = "FK_USER_PASSWORD_TOKEN"))
    private User user;


    public PasswordResetToken(String token, User user) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME) ;
        this.user = user;
    }

    public PasswordResetToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME) ;
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance ();
        calendar.setTimeInMillis(new Date().getTime () );
        calendar.add ( Calendar.MINUTE, expirationTime );
        return new Date(calendar.getTime ().getTime ());
    }
}
