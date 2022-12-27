package com.softarex.portal.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private final String oldPassword;
    private final String newPassword;
}
