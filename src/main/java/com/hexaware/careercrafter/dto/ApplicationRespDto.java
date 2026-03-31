package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.ApplicationStatus;

import java.time.LocalDate;

public record ApplicationRespDto(
        long id,
        LocalDate appliedDate,
        ApplicationStatus status,
        String coverLetter,
        String resumeUrl,
        String jobTitle,
        String companyName,
        String seekerName
) {
}
