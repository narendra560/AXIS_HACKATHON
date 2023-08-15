package com.axis.hackathon.jobservice.repository;

import com.axis.hackathon.jobservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {

    List<Job> findByJobPostedBy(String userId);

    List<Job> findByJobTitleContaining(String jobTitle);

}
