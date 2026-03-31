package com.hexaware.careercrafter.controller;

import com.hexaware.careercrafter.dto.ApplicationReqDto;
import com.hexaware.careercrafter.dto.ApplicationRespDto;
import com.hexaware.careercrafter.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    // Job Seeker applies to an existing Job Listing
    @PostMapping("/apply/{seekerId}/{jobId}")
    public ResponseEntity<?> applyToJob(@Valid @RequestBody ApplicationReqDto applicationReqDto,
                                        @PathVariable long seekerId,
                                        @PathVariable long jobId) {
        jobApplicationService.applyToJob(applicationReqDto, seekerId, jobId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    // GET API -> table, stats [for Frontend APP screen]
    @GetMapping("/seeker/{seekerId}")
    public List<ApplicationRespDto> getApplicationsBySeekerId(@PathVariable long seekerId) {
        return jobApplicationService.getApplicationsBySeekerId(seekerId);
    }

    // Employer views all applications for a job
    @GetMapping("/job/{jobId}")
    public List<ApplicationRespDto> getApplicationsByJobId(@PathVariable long jobId) {
        return jobApplicationService.getApplicationsByJobId(jobId);
    }

    // Employer updates application status
    @PutMapping("/update-status/{applicationId}")
    public ResponseEntity<?> updateApplicationStatus(@PathVariable long applicationId,
                                                     @RequestParam String status) {
        jobApplicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}

