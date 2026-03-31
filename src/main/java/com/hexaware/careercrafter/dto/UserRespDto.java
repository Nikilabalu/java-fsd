package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.UserRole;

public record UserRespDto(
        long id,
        String name,
        String email,
        UserRole userRole,
        String phone,
        String location
) {
}