package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationReqDto(
        @NotBlank(message = "coverLetter cannot be blank")
        @NotNull
        String coverLetter,

        String resumeUrl
) {
}
