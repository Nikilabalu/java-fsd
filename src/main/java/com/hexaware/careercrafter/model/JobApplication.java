package com.hexaware.careercrafter.model;

import com.hexaware.careercrafter.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate appliedDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String coverLetter;

    private String resumeUrl;

    @ManyToOne
    private JobListing jobListing;

    @ManyToOne
    private User jobSeeker;
}
