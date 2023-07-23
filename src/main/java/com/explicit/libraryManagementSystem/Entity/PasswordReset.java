package com.explicit.libraryManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordReset {
    private Long id;
    public String email;
    public String oldPassword;
    public String newPassword;

    public void setConfirmPassword(String confirmPassword) {
    }
}
