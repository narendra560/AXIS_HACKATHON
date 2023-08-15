package com.axis.hackathon.jobservice.repository;

import com.axis.hackathon.jobservice.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,String> {
    List<JobApplication> findByUserId(String userId);

    List<JobApplication> findByJobIdOrderByResumeScoreDesc(String jobId);

}
