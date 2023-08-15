package com.axis.hackathon.jobservice.service;

import com.axis.hackathon.jobservice.model.Job;
import com.axis.hackathon.jobservice.model.JobApplication;

import java.util.List;

public interface JobService {

    public String createJob(Job job) throws Exception;
    public String deleteJob(String jobId) throws Exception;
    public Job getJob(String jobId) throws Exception;
    public String updateJob(String jobId,Job job) throws Exception;
    public String applyJob(JobApplication jobApplication) throws Exception;
    public String deleteJobApplication(String applicationId) throws Exception;
    public List<Job> getAllJobsPostedByMe(String userId) throws Exception;
    public List<JobApplication> getAllJobsAppliedByMe(String userId) throws Exception;
    public List<JobApplication> getAllApplicationsForJob(String jobId) throws Exception;
    public List<Job> searchJob(String jobTitle) throws Exception;

    public String updateApplication(String applicationId,String summary,int score,String remarks) throws Exception;

    public String shortListCandidates(String jobId,Long count) throws Exception;

    public List<Job> fetchLatestJobs() throws Exception;
}
