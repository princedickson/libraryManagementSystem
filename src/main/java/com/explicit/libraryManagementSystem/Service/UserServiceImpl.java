package com.explicit.libraryManagementSystem.Service;

import com.explicit.libraryManagementSystem.Entity.PasswordReset;
import com.explicit.libraryManagementSystem.Entity.PasswordResetToken;
import com.explicit.libraryManagementSystem.Entity.Roles;
import com.explicit.libraryManagementSystem.Entity.User;
import com.explicit.libraryManagementSystem.Repository.PasswordResetRepository;
import com.explicit.libraryManagementSystem.Repository.UserRepository;
import com.explicit.libraryManagementSystem.Web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class UserServiceImpl implements UserService {

    private PasswordResetRepository passwordResetRepository;

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder,
                           @Lazy PasswordResetRepository passwordResetRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetRepository = passwordResetRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {

        //---perform validation---


        //----Handle Validation----//

        if (userRegistrationDto == null) {
            throw new IllegalArgumentException("User registration DTO cannot be null");
        }


        // ----Create and save the user----//

        User user = new User(
                userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                passwordEncoder.encode(userRegistrationDto.getPassword()),
                Arrays.asList(new Roles("user_role"))
        );

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        //----check if username exit in DB or not----//
        if (user == null) {
            throw new UsernameNotFoundException("invalid username or password");
        }
        return  new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(),mapRolesToAuthorities(user.getRole()));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                user.getRole().stream().map(roles -> new SimpleGrantedAuthority(roles.getName()))
//                        .collect(Collectors.toList()));

    }


    // load user details from the database and create a UserDetails object
    // mapping roles to authorities using a lambda expression and stream operations

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> role) {
        return role.stream().map(roles -> new SimpleGrantedAuthority("Role_User" + roles.getName())).collect(Collectors.toList());

    }

    @Override
    public User fingByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetToken(User user, String token) {

        PasswordResetToken passwordResetToken = new PasswordResetToken( token, user);
        passwordResetRepository.save(passwordResetToken);

    }

    @Override
    public String validatePasswordReset(String token) {
       PasswordResetToken passwordResetToken =
               passwordResetRepository.findByToken(token);

       // check is password is null

       if(passwordResetToken == null){
           return "invalid";
       }

       User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            passwordResetRepository.delete(passwordResetToken);
            return "expired";
        }
        return "true";
    }

    @Override
    public Optional<User> getUserByPasswordToken(String token) {
        return Optional.ofNullable(passwordResetRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }

}
