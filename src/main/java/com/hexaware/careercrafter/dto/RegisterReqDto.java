package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterReqDto(
        @NotBlank(message = "name cannot be blank")
        @NotNull
        @Size(min = 2, max = 100, message = "name size is defined as 2-100")
        String name,

        @NotBlank(message = "email cannot be blank")
        @Email(message = "enter a valid email")
        String email,

        @NotBlank(message = "password cannot be blank")
        @Size(min = 6, message = "password must be at least 6 characters")
        String password,

        @NotNull(message = "role cannot be null")
        UserRole role,

        String phone,

        String location
) {
}
/* Record has all-args constructor and accessors / getter */
