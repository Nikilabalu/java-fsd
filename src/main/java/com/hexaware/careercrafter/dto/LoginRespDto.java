package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.UserRole;

public record LoginRespDto(
        long id,
        String name,
        String email,
        UserRole role,
        String token
) {
}
