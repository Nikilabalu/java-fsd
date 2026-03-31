package com.hexaware.careercrafter.dto;

public record ResumeRespDto(
        long id,
        String summary,
        String skills,
        String educationDetails,
        String workExperience,
        String certifications,
        String linkedInUrl,
        String githubUrl,
        String resumeFileUrl,
        Boolean isPublic,
        String ownerName,
        String ownerEmail
) {
}
