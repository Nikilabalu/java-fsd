package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginReqDto(
        @NotBlank(message = "email cannot be blank")
        @Email(message = "enter a valid email")
        String email,

        @NotBlank(message = "password cannot be blank")
        String password
) {
}
