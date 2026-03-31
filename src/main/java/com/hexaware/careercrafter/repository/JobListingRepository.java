package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.enums.ExperienceLevel;
import com.hexaware.careercrafter.enums.JobType;
import com.hexaware.careercrafter.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {

    @Query("""
        select j
        from JobListing j
        where (?1 IS NULL OR j.jobTitle LIKE %?1%)
        AND (?2 IS NULL OR j.location LIKE %?2%)
        AND (?3 IS NULL OR j.jobType = ?3)
        AND (?4 IS NULL OR j.experienceLevel = ?4)
        AND j.isActive = true
        """)
    List<JobListing> getByFilter(String jobTitle, String location, JobType jobType, ExperienceLevel experienceLevel);

    @Query("""
            select j
            from JobListing j
            where j.employer.id = ?1
            """)
    List<JobListing> getJobListingsByEmployerId(long employerId);
}
