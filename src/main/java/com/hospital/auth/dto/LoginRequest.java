package com.hospital.auth.dto;

import com.hospital.common.validation.ValidationMessages;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(
    		  message = ValidationMessages.REQUIRED
    		)
    private String username;

    @NotBlank(
    		  message = ValidationMessages.REQUIRED
    		)
    private String password;
}