package com.softarex.portal.dto;

import lombok.Data;

@Data
//TODO validation
public class RegistrationDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
