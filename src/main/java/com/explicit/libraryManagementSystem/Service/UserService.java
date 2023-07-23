package com.explicit.libraryManagementSystem.Service;

import com.explicit.libraryManagementSystem.Entity.User;
import com.explicit.libraryManagementSystem.Web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User fingByEmail(String email);

    void createPasswordResetToken(User user, String token);

    String validatePasswordReset(String token);

    Optional<User> getUserByPasswordToken(String token);

    void changePassword(User user, String newPassword);
}
