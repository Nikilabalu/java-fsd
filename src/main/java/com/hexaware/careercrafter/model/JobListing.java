package com.hexaware.careercrafter.model;

import com.hexaware.careercrafter.enums.ExperienceLevel;
import com.hexaware.careercrafter.enums.JobType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
 CareerCrafter Portal
 ----------------------
 Employer  1:M  JobListing
           M:1
 JobSeeker M:M  JobListing  :- 2 ManyToOne
           job_application
           id
           seeker_id
           job_id
           applied_date
           status
           cover_letter
           resume_url
*/

@Entity
@Table(name = "job_listings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String jobTitle;

    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience_level")
    private ExperienceLevel experienceLevel;

    private Double minSalary;

    private Double maxSalary;

    private String skillsRequired;

    private String qualifications;

    private LocalDate postedDate;

    private LocalDate applicationDeadline;

    private Boolean isActive;

    @ManyToOne
    private User employer;
}
