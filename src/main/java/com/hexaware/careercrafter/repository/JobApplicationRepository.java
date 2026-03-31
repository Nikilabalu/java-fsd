package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    @Query("""
            select a
            from JobApplication a
            where a.jobSeeker.id = ?1
            """)
    List<JobApplication> getApplicationsBySeekerId(long seekerId);

    @Query("""
            select a
            from JobApplication a
            where a.jobListing.id = ?1
            """)
    List<JobApplication> getApplicationsByJobId(long jobId);

    @Query("""
            select count(a) > 0
            from JobApplication a
            where a.jobSeeker.id = ?1 AND a.jobListing.id = ?2
            """)
    boolean hasAlreadyApplied(long seekerId, long jobId);
}
