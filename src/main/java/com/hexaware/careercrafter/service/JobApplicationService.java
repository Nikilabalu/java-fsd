package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.ApplicationReqDto;
import com.hexaware.careercrafter.dto.ApplicationRespDto;
import com.hexaware.careercrafter.enums.ApplicationStatus;
import com.hexaware.careercrafter.exceptions.ResourceNotFoundException;
import com.hexaware.careercrafter.mapper.JobApplicationMapper;
import com.hexaware.careercrafter.model.JobApplication;
import com.hexaware.careercrafter.model.JobListing;
import com.hexaware.careercrafter.model.User;
import com.hexaware.careercrafter.repository.JobApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserService userService;
    private final JobListingService jobListingService;

    public void applyToJob(ApplicationReqDto applicationReqDto, long seekerId, long jobId) {
        // Step 1: Fetch JobSeeker by seekerId
        User jobSeeker = userService.getById(seekerId);
        // Step 2: Fetch JobListing by jobId
        JobListing jobListing = jobListingService.getJobListingById(jobId);
        // Step 3: Check if already applied
        boolean alreadyApplied = jobApplicationRepository.hasAlreadyApplied(seekerId, jobId);
        if (alreadyApplied) {
            throw new ResourceNotFoundException("You have already applied to this job");
        }
        // Step 4: Map dto to entity
        JobApplication jobApplication = JobApplicationMapper.mapToEntity(applicationReqDto);
        // Step 5: Attach jobSeeker and jobListing
        jobApplication.setJobSeeker(jobSeeker);
        jobApplication.setJobListing(jobListing);
        // Step 6: Save in DB
        jobApplicationRepository.save(jobApplication);
    }

    public List<ApplicationRespDto> getApplicationsBySeekerId(long seekerId) {
        return jobApplicationRepository.getApplicationsBySeekerId(seekerId)
                .stream()
                .map(JobApplicationMapper::mapToDto)
                .toList();
    }

    public List<ApplicationRespDto> getApplicationsByJobId(long jobId) {
        return jobApplicationRepository.getApplicationsByJobId(jobId)
                .stream()
                .map(JobApplicationMapper::mapToDto)
                .toList();
    }

    public void updateApplicationStatus(long applicationId, String status) {
        // Step 1: Fetch application by id
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid application id given"));
        // Step 2: Update status
        jobApplication.setStatus(ApplicationStatus.valueOf(status));
        // Step 3: Save
        jobApplicationRepository.save(jobApplication);
    }
}
