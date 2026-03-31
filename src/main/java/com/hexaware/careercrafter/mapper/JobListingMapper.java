package com.hexaware.careercrafter.mapper;

import com.hexaware.careercrafter.dto.JobListingPageRespDto;
import com.hexaware.careercrafter.dto.JobListingReqDto;
import com.hexaware.careercrafter.dto.JobListingRespDto;
import com.hexaware.careercrafter.model.JobListing;

import java.time.LocalDate;
import java.util.List;

public class JobListingMapper {

    public static JobListing mapToEntity(JobListingReqDto jobListingReqDto) {
        JobListing jobListing = new JobListing();
        jobListing.setJobTitle(jobListingReqDto.jobTitle());       // accessor
        jobListing.setCompanyName(jobListingReqDto.companyName());
        jobListing.setJobDescription(jobListingReqDto.jobDescription());
        jobListing.setLocation(jobListingReqDto.location());
        jobListing.setJobType(jobListingReqDto.jobType());
        jobListing.setExperienceLevel(jobListingReqDto.experienceLevel());
        jobListing.setMinSalary(jobListingReqDto.minSalary());
        jobListing.setMaxSalary(jobListingReqDto.maxSalary());
        jobListing.setSkillsRequired(jobListingReqDto.skillsRequired());
        jobListing.setQualifications(jobListingReqDto.qualifications());
        jobListing.setApplicationDeadline(jobListingReqDto.applicationDeadline());
        jobListing.setPostedDate(LocalDate.now());
        jobListing.setIsActive(true);
        return jobListing;
    }

    public static JobListingRespDto mapToDto(JobListing jobListing) {
        return new JobListingRespDto(
                jobListing.getId(),
                jobListing.getJobTitle(),
                jobListing.getCompanyName(),
                jobListing.getJobDescription(),
                jobListing.getLocation(),
                jobListing.getJobType(),
                jobListing.getExperienceLevel(),
                jobListing.getMinSalary(),
                jobListing.getMaxSalary(),
                jobListing.getSkillsRequired(),
                jobListing.getQualifications(),
                jobListing.getPostedDate(),
                jobListing.getApplicationDeadline(),
                jobListing.getIsActive(),
                jobListing.getEmployer().getName()
        );
    }

    public static JobListingPageRespDto mapToPageDto(List<JobListingRespDto> data,
                                                     long totalRecords,
                                                     int totalPages) {
        return new JobListingPageRespDto(data, totalRecords, totalPages);
    }
}
