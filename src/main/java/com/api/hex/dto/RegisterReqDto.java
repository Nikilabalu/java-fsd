package com.api.hex.dto;

import com.api.hex.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterReqDto(
        @NotBlank(message = "username cannot be blank")
        @Email(message = "enter a valid email")
        String username,
        @NotBlank(message = "password cannot be blank")
        @Size(min = 6, message = "password must be at least 6 characters")
        String password,
        @NotNull(message = "role cannot be null")
        UserRole role) {
}
