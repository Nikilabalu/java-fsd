package com.hexaware.careercrafter.dto;

public record ResumeReqDto(
        String summary,
        String skills,
        String educationDetails,
        String workExperience,
        String certifications,
        String linkedInUrl,
        String githubUrl,
        String resumeFileUrl,
        Boolean isPublic
) {
}
