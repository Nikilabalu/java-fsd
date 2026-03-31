package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.ExperienceLevel;
import com.hexaware.careercrafter.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record JobListingReqDto(
        @NotBlank(message = "jobTitle cannot be blank")
        @NotNull
        String jobTitle,

        @NotBlank(message = "companyName cannot be blank")
        String companyName,

        String jobDescription,

        String location,

        JobType jobType,

        ExperienceLevel experienceLevel,

        Double minSalary,

        Double maxSalary,

        String skillsRequired,

        String qualifications,

        LocalDate applicationDeadline
) {
}
