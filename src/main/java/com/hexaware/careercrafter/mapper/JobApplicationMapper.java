package com.hexaware.careercrafter.mapper;

import com.hexaware.careercrafter.dto.ApplicationReqDto;
import com.hexaware.careercrafter.dto.ApplicationRespDto;
import com.hexaware.careercrafter.enums.ApplicationStatus;
import com.hexaware.careercrafter.model.JobApplication;

import java.time.LocalDate;

public class JobApplicationMapper {

    public static JobApplication mapToEntity(ApplicationReqDto applicationReqDto) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setCoverLetter(applicationReqDto.coverLetter());  // accessor
        jobApplication.setResumeUrl(applicationReqDto.resumeUrl());
        jobApplication.setAppliedDate(LocalDate.now());
        jobApplication.setStatus(ApplicationStatus.APPLIED);
        return jobApplication;
    }

    public static ApplicationRespDto mapToDto(JobApplication jobApplication) {
        return new ApplicationRespDto(
                jobApplication.getId(),
                jobApplication.getAppliedDate(),
                jobApplication.getStatus(),
                jobApplication.getCoverLetter(),
                jobApplication.getResumeUrl(),
                jobApplication.getJobListing().getJobTitle(),
                jobApplication.getJobListing().getCompanyName(),
                jobApplication.getJobSeeker().getName()
        );
    }
}
