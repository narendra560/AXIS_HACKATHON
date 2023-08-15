package com.axis.hackathon.jobservice.controller;

import com.axis.hackathon.jobservice.model.Job;
import com.axis.hackathon.jobservice.model.JobApplication;
import com.axis.hackathon.jobservice.service.JobService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class JobController {

//    Write post mapping to create job.  userId is request header
    @Autowired
    JobService jobService;

    @PostMapping("/job/create")
    public ResponseEntity<?> createJob(@RequestHeader String userId, @RequestBody Job job) throws Exception {
        job.setJobPostedBy(userId);
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @DeleteMapping("/job/delete/{jobId}")
    public ResponseEntity<?> deleteJob(@RequestHeader String userId, @PathVariable String jobId) throws Exception {
        return ResponseEntity.ok(jobService.deleteJob(jobId));
    }

    @CrossOrigin
    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getJob(@RequestHeader String userId, @PathVariable String jobId) throws Exception {
        return ResponseEntity.ok(jobService.getJob(jobId));
    }

    @PostMapping("/job/update/{jobId}")
    public ResponseEntity<?> updateJob(@RequestHeader String userId, @PathVariable String jobId, @RequestBody Job job) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(jobId, job));
    }

    @PostMapping("/job/apply")
    public ResponseEntity<?> applyJob(@RequestHeader String userId, @RequestBody JobApplication job) throws Exception {
        job.setUserId(userId);
        return ResponseEntity.ok(jobService.applyJob(job));
    }

    @DeleteMapping("/job/deleteApplication/{applicationId}")
    public ResponseEntity<?> deleteJobApplication(@RequestHeader String userId, @PathVariable String applicationId) throws Exception {
        return ResponseEntity.ok(jobService.deleteJobApplication(applicationId));
    }
    @GetMapping("/job/getAllJobsPostedByMe")
    public ResponseEntity<?> getAllJobsPostedByMe(@RequestHeader String userId) throws Exception {
        return ResponseEntity.ok(jobService.getAllJobsPostedByMe(userId));
    }

    @GetMapping("/job/getAllJobsAppliedByMe")
    public ResponseEntity<?> getAllJobsAppliedByMe(@RequestHeader String userId) throws Exception {
        return ResponseEntity.ok(jobService.getAllJobsAppliedByMe(userId));
    }

    @GetMapping("/job/getAllApplicationsForJob/{jobId}")
    public ResponseEntity<?> getAllApplicationsForJob(@RequestHeader String userId, @PathVariable String jobId) throws Exception {
        return ResponseEntity.ok(jobService.getAllApplicationsForJob(jobId));
    }

    @GetMapping("/job/searchJob/{jobTitle}")
    public ResponseEntity<?> searchJob(@RequestHeader String userId, @PathVariable String jobTitle) throws Exception {
        return ResponseEntity.ok(jobService.searchJob(jobTitle));
    }

    @PutMapping("/job/application/update/{applicationId}")
    public ResponseEntity<?> updateApplication(@PathVariable String applicationId,@RequestParam String summary,@RequestParam String remarks, @RequestParam int score) throws Exception {
        return ResponseEntity.ok(jobService.updateApplication(applicationId,summary,score,remarks));
    }

    @GetMapping("/all/jobs")
    public ResponseEntity<?> getAllJobs() throws Exception{
        return ResponseEntity.ok(jobService.fetchLatestJobs());
    }
}
