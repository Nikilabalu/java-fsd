package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.enums.ExperienceLevel;
import com.hexaware.careercrafter.enums.JobType;

public record JobFilterReqDto(
        String jobTitle,
        String location,
        JobType jobType,
        ExperienceLevel experienceLevel
) {
}
