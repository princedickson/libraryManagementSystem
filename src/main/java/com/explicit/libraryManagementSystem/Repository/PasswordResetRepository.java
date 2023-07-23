package com.explicit.libraryManagementSystem.Repository;

import com.explicit.libraryManagementSystem.Entity.PasswordReset;
import com.explicit.libraryManagementSystem.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
