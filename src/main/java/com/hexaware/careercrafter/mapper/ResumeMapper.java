package com.hexaware.careercrafter.mapper;

import com.hexaware.careercrafter.dto.ResumeReqDto;
import com.hexaware.careercrafter.dto.ResumeRespDto;
import com.hexaware.careercrafter.model.Resume;

public class ResumeMapper {

    public static Resume mapToEntity(ResumeReqDto resumeReqDto) {
        Resume resume = new Resume();
        resume.setSummary(resumeReqDto.summary());            // accessor
        resume.setSkills(resumeReqDto.skills());
        resume.setEducationDetails(resumeReqDto.educationDetails());
        resume.setWorkExperience(resumeReqDto.workExperience());
        resume.setCertifications(resumeReqDto.certifications());
        resume.setLinkedInUrl(resumeReqDto.linkedInUrl());
        resume.setGithubUrl(resumeReqDto.githubUrl());
        resume.setResumeFileUrl(resumeReqDto.resumeFileUrl());
        resume.setIsPublic(resumeReqDto.isPublic());
        return resume;
    }

    public static ResumeRespDto mapToDto(Resume resume) {
        return new ResumeRespDto(
                resume.getId(),
                resume.getSummary(),
                resume.getSkills(),
                resume.getEducationDetails(),
                resume.getWorkExperience(),
                resume.getCertifications(),
                resume.getLinkedInUrl(),
                resume.getGithubUrl(),
                resume.getResumeFileUrl(),
                resume.getIsPublic(),
                resume.getUser().getName(),
                resume.getUser().getEmail()
        );
    }
}
