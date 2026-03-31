package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.JobFilterReqDto;
import com.hexaware.careercrafter.dto.JobListingPageRespDto;
import com.hexaware.careercrafter.dto.JobListingReqDto;
import com.hexaware.careercrafter.dto.JobListingRespDto;
import com.hexaware.careercrafter.exceptions.ResourceNotFoundException;
import com.hexaware.careercrafter.mapper.JobListingMapper;
import com.hexaware.careercrafter.model.JobListing;
import com.hexaware.careercrafter.model.User;
import com.hexaware.careercrafter.repository.JobListingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobListingService {

    private final JobListingRepository jobListingRepository;
    private final UserService userService;

    public void addJobListing(JobListingReqDto jobListingReqDto, long employerId) {
        // Step 1: Fetch Employer by employerId
        User employer = userService.getById(employerId);
        // Step 2: Map dto to entity
        JobListing jobListing = JobListingMapper.mapToEntity(jobListingReqDto);
        // Step 3: Attach employer to jobListing
        jobListing.setEmployer(employer);
        // Step 4: Save in DB
        jobListingRepository.save(jobListing);
    }

    public JobListingPageRespDto getAllJobListings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobListing> jobListingPage = jobListingRepository.findAll(pageable);
        List<JobListingRespDto> data = jobListingPage.getContent()
                .stream()
                .map(JobListingMapper::mapToDto)
                .toList();
        return JobListingMapper.mapToPageDto(data, jobListingPage.getTotalElements(), jobListingPage.getTotalPages());
    }

    public JobListingRespDto getById(long id) {
        JobListing jobListing = jobListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid job id given"));
        return JobListingMapper.mapToDto(jobListing);
    }

    public JobListing getJobListingById(long id) {
        return jobListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid job id given"));
    }

    public List<JobListingRespDto> getByFilter(JobFilterReqDto jobFilterReqDto) {
        return jobListingRepository
                .getByFilter(
                        jobFilterReqDto.jobTitle(),
                        jobFilterReqDto.location(),
                        jobFilterReqDto.jobType(),
                        jobFilterReqDto.experienceLevel()
                )
                .stream()
                .map(JobListingMapper::mapToDto)
                .toList();
    }

    public List<JobListingRespDto> getJobListingsByEmployerId(long employerId) {
        return jobListingRepository.getJobListingsByEmployerId(employerId)
                .stream()
                .map(JobListingMapper::mapToDto)
                .toList();
    }

    public void updateJobListing(long jobId, JobListingReqDto jobListingReqDto) {
        // Step 1: Fetch existing job listing
        JobListing existing = jobListingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid job id given"));
        // Step 2: Update fields
        existing.setJobTitle(jobListingReqDto.jobTitle());
        existing.setCompanyName(jobListingReqDto.companyName());
        existing.setJobDescription(jobListingReqDto.jobDescription());
        existing.setLocation(jobListingReqDto.location());
        existing.setJobType(jobListingReqDto.jobType());
        existing.setExperienceLevel(jobListingReqDto.experienceLevel());
        existing.setMinSalary(jobListingReqDto.minSalary());
        existing.setMaxSalary(jobListingReqDto.maxSalary());
        existing.setSkillsRequired(jobListingReqDto.skillsRequired());
        existing.setQualifications(jobListingReqDto.qualifications());
        existing.setApplicationDeadline(jobListingReqDto.applicationDeadline());
        // Step 3: Save updated entity
        jobListingRepository.save(existing);
    }

    public void deleteJobListing(long jobId) {
        jobListingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid job id given"));
        jobListingRepository.deleteById(jobId);
    }
}
