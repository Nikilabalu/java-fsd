package com.hexaware.careercrafter.controller;

import com.hexaware.careercrafter.dto.JobFilterReqDto;
import com.hexaware.careercrafter.dto.JobListingPageRespDto;
import com.hexaware.careercrafter.dto.JobListingReqDto;
import com.hexaware.careercrafter.dto.JobListingRespDto;
import com.hexaware.careercrafter.enums.ExperienceLevel;
import com.hexaware.careercrafter.enums.JobType;
import com.hexaware.careercrafter.service.JobListingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
public class JobListingController {

    private final JobListingService jobListingService;


    @PostMapping("/add/{employerId}")
    public ResponseEntity<?> addJobListing(@Valid @RequestBody JobListingReqDto jobListingReqDto,
                                           @PathVariable long employerId) {
        jobListingService.addJobListing(jobListingReqDto, employerId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


    @GetMapping("/get-all")
    public JobListingPageRespDto getAllJobListings(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return jobListingService.getAllJobListings(page, size);
    }

    @GetMapping("/get/{id}")
    public JobListingRespDto getById(@PathVariable long id) {
        return jobListingService.getById(id);
    }


    @PostMapping("/get/filter")
    public List<JobListingRespDto> getByFilter(@RequestBody JobFilterReqDto jobFilterReqDto) {
        return jobListingService.getByFilter(jobFilterReqDto);
    }


    @GetMapping("/employer/{employerId}")
    public List<JobListingRespDto> getJobListingsByEmployerId(@PathVariable long employerId) {
        return jobListingService.getJobListingsByEmployerId(employerId);
    }


    @PutMapping("/update/{jobId}")
    public ResponseEntity<?> updateJobListing(@PathVariable long jobId,
                                              @Valid @RequestBody JobListingReqDto jobListingReqDto) {
        jobListingService.updateJobListing(jobId, jobListingReqDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<?> deleteJobListing(@PathVariable long jobId) {
        jobListingService.deleteJobListing(jobId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/job-types")
    public List<String> getJobTypes() {
        return Arrays.stream(JobType.values())
                .map(Enum::name)
                .toList();
    }

    @GetMapping("/experience-levels")
    public List<String> getExperienceLevels() {
        return Arrays.stream(ExperienceLevel.values())
                .map(Enum::name)
                .toList();
    }
}
