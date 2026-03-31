package com.hexaware.careercrafter.controller;

import com.hexaware.careercrafter.dto.ResumeReqDto;
import com.hexaware.careercrafter.dto.ResumeRespDto;
import com.hexaware.careercrafter.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@AllArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    // Job Seeker adds resume
    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addResume(@RequestBody ResumeReqDto resumeReqDto,
                                       @PathVariable long userId) {
        resumeService.addResume(resumeReqDto, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    // GET API -> Resume details for Frontend screen
    @GetMapping("/get/user/{userId}")
    public ResumeRespDto getResumeByUserId(@PathVariable long userId) {
        return resumeService.getResumeByUserId(userId);
    }

    // Job Seeker updates resume
    @PutMapping("/update/{resumeId}")
    public ResponseEntity<?> updateResume(@PathVariable long resumeId,
                                          @RequestBody ResumeReqDto resumeReqDto) {
        resumeService.updateResume(resumeId, resumeReqDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
