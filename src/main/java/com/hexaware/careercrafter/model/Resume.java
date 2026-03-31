package com.hexaware.careercrafter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resumes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private String skills;

    private String educationDetails;

    private String workExperience;

    private String certifications;

    private String linkedInUrl;

    private String githubUrl;

    private String resumeFileUrl;

    private Boolean isPublic;

    @ManyToOne
    private User user;
}
