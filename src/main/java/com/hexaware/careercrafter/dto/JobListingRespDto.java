package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.ExperienceLevel;
import com.hexaware.careercrafter.enums.JobType;

import java.time.LocalDate;

public record JobListingRespDto(
        long id,
        String jobTitle,
        String companyName,
        String jobDescription,
        String location,
        JobType jobType,
        ExperienceLevel experienceLevel,
        Double minSalary,
        Double maxSalary,
        String skillsRequired,
        String qualifications,
        LocalDate postedDate,
        LocalDate applicationDeadline,
        Boolean isActive,
        String employerName
) {
}
