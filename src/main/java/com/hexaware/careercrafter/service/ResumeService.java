package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.ResumeReqDto;
import com.hexaware.careercrafter.dto.ResumeRespDto;
import com.hexaware.careercrafter.exceptions.ResourceNotFoundException;
import com.hexaware.careercrafter.mapper.ResumeMapper;
import com.hexaware.careercrafter.model.Resume;
import com.hexaware.careercrafter.model.User;
import com.hexaware.careercrafter.repository.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserService userService;

    public void addResume(ResumeReqDto resumeReqDto, long userId) {
        // Step 1: Fetch User by userId
        User user = userService.getById(userId);
        // Step 2: Map dto to entity
        Resume resume = ResumeMapper.mapToEntity(resumeReqDto);
        // Step 3: Attach user to resume
        resume.setUser(user);
        // Step 4: Save in DB
        resumeRepository.save(resume);
    }

    public ResumeRespDto getResumeByUserId(long userId) {
        Resume resume = resumeRepository.getResumeByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found for user id: " + userId));
        return ResumeMapper.mapToDto(resume);
    }

    public void updateResume(long resumeId, ResumeReqDto resumeReqDto) {
        // Step 1: Fetch existing resume
        Resume existing = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid resume id given"));
        // Step 2: Update fields
        existing.setSummary(resumeReqDto.summary());
        existing.setSkills(resumeReqDto.skills());
        existing.setEducationDetails(resumeReqDto.educationDetails());
        existing.setWorkExperience(resumeReqDto.workExperience());
        existing.setCertifications(resumeReqDto.certifications());
        existing.setLinkedInUrl(resumeReqDto.linkedInUrl());
        existing.setGithubUrl(resumeReqDto.githubUrl());
        existing.setResumeFileUrl(resumeReqDto.resumeFileUrl());
        existing.setIsPublic(resumeReqDto.isPublic());
        // Step 3: Save
        resumeRepository.save(existing);
    }
}
