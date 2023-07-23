package com.explicit.libraryManagementSystem.Web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
