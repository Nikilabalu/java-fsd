package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query("""
            select r
            from Resume r
            where r.user.id = ?1
            """)
    Optional<Resume> getResumeByUserId(long userId);
}
