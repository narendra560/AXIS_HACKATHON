package com.axis.hackathon.jobservice.service;

import com.aifarms.java_common.exceptions.BadRequestException;
import com.axis.hackathon.jobservice.model.Job;
import com.axis.hackathon.jobservice.model.JobApplication;
import com.axis.hackathon.jobservice.repository.JobApplicationRepository;
import com.axis.hackathon.jobservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.axis.hackathon.jobservice.external.GenerationService;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Autowired
    GenerationService generationService;

    private Map<String,String> emails = new HashMap<>();

    public JobServiceImpl(){
        emails.put("101","narendracse560@gmail.com");
        emails.put("102","krishnajanjanam09@gmail.com");
        emails.put("103","narendragadidamalla@gmail.com");
        emails.put("104","sumanthvirat17@gmail.com");
    }

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    private String generateId(){
        return UUID.randomUUID().toString();
    }

    private String generateJobId(){
        long jobs = jobRepository.count();
        String s = "" + 1000 + jobs;
        return s;
    }

    @Override
    public String createJob(Job job) throws Exception {

        if(job == null){
            throw new BadRequestException("Job is mandatory");
        }
        if(job.getJobTitle() == null || job.getJobTitle().isEmpty()){
            throw new BadRequestException("Job Title is mandatory");
        }
        if(job.getJobDescription() == null || job.getJobDescription().isEmpty()){
            throw new BadRequestException("Job Description is mandatory");
        }
        if(job.getJobLocation() == null || job.getJobLocation().isEmpty()){
            throw new BadRequestException("Job Location is mandatory");
        }
        if(job.getJobType() == null || job.getJobType().isEmpty()){
            throw new BadRequestException("Job Type is mandatory");
        }
        if(job.getJobCategory() == null || job.getJobCategory().isEmpty()){
            throw new BadRequestException("Job Category is mandatory");
        }
        if(job.getJobPostedBy() == null || job.getJobPostedBy().isEmpty()){
            throw new BadRequestException("Userid is mandatory");
        }
        try{
            List<String> skills = generationService.getSkills(job.getJobDescription());
            logger.info(skills.toString());
            job.setSkills(skills);
        }catch (Exception e){
            logger.error("Error while generating skills from job description",e);
        }
        String jobId = generateJobId();
        Map<String,Object> req = new HashMap<>();
        req.put("email",emails.get(job.getJobPostedBy()));
        req.put("event_id","job_posted");
        job.setJobId(jobId);
        job.setJobPostedOn(LocalDateTime.now());
        job.setJobStatus("Active");
        jobRepository.saveAndFlush(job);

        return jobId;
    }

    @Override
    public String deleteJob(String jobId) throws Exception {
        if(jobId == null || jobId.isEmpty()){
            throw new BadRequestException("Job Id is mandatory");
        }
        Optional<Job> job = jobRepository.findById(jobId);
        if(job.isEmpty()){
            throw new BadRequestException("Job not found");
        }
        job.get().setJobStatus("Inactive");
        jobRepository.saveAndFlush(job.get());
        return "Job Deleted Successfully";
    }

    @Override
    public Job getJob(String jobId) throws Exception {
        if(jobId == null || jobId.isEmpty()){
            throw new BadRequestException("Job Id is mandatory");
        }
        Optional<Job> job = jobRepository.findById(jobId);
        if(job.isEmpty()){
            throw new BadRequestException("Job not found");
        }
        return job.get();
    }

    @Override
    public String updateJob(String jobId, Job job) throws Exception {
        if(jobId == null || jobId.isEmpty()){
            throw new BadRequestException("Job Id is mandatory");
        }
        if(job == null){
            throw new BadRequestException("Job is mandatory");
        }
        if(job.getJobTitle() == null || job.getJobTitle().isEmpty()){
            throw new BadRequestException("Job Title is mandatory");
        }
        if(job.getJobDescription() == null || job.getJobDescription().isEmpty()){
            throw new BadRequestException("Job Description is mandatory");
        }
        if(job.getJobLocation() == null || job.getJobLocation().isEmpty()){
            throw new BadRequestException("Job Location is mandatory");
        }
        if(job.getJobType() == null || job.getJobType().isEmpty()){
            throw new BadRequestException("Job Type is mandatory");
        }
        if(job.getJobCategory() == null || job.getJobCategory().isEmpty()){
            throw new BadRequestException("Job Category is mandatory");
        }
        if(job.getJobPostedBy() == null || job.getJobPostedBy().isEmpty()){
            throw new BadRequestException("Userid is mandatory");
        }
        Optional<Job> job1 = jobRepository.findById(jobId);
        if(job1.isEmpty()){
            throw new BadRequestException("Job not found");
        }
        jobRepository.saveAndFlush(job);
        return "Job Updated Successfully";
    }

    @Override
    public String applyJob(JobApplication jobApplication) throws Exception {
        if(jobApplication == null){
            throw new BadRequestException("Job Application is mandatory");
        }
        if(jobApplication.getJobId() == null || jobApplication.getJobId().isEmpty()){
            throw new BadRequestException("Job Id is mandatory");
        }
        if(jobApplication.getUserId() == null || jobApplication.getUserId().isEmpty()){
            throw new BadRequestException("User Id is mandatory");
        }
        if(jobApplication.getResumePath()== null || jobApplication.getResumePath().isEmpty()){
            throw new BadRequestException("Resume is mandatory");
        }
        Optional<Job> job = jobRepository.findById(jobApplication.getJobId());
        if(job.isEmpty()){
            throw new BadRequestException("Job not found");
        }
        String applicationId = generateId();
        jobApplication.setApplicationId(applicationId);
        jobApplication.setAppliedOn(LocalDateTime.now());
        jobApplication.setApplicationStatus("Applied");
        jobApplicationRepository.saveAndFlush(jobApplication);
        Map<String,String> mailMap = new HashMap<>();
        mailMap.put("job_title",job.get().getJobTitle());
        mailMap.put("event_id","applied");
        mailMap.put("application_id",applicationId);
        mailMap.put("recipient_name","user-"+jobApplication.getUserId());
        mailMap.put("email",emails.get(jobApplication.getUserId()));
        generationService.sendMail(mailMap);
        //CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> { return generationService.updateApplication(applicationId,job.get().getJobDescription(),jobApplication.getResumePath());});
        generationService.updateApplication(applicationId,job.get().getJobDescription(),jobApplication.getResumePath());
        return applicationId;
    }

    @Override
    public String deleteJobApplication(String applicationId) throws Exception {
        if(applicationId == null || applicationId.isEmpty()){
            throw new BadRequestException("Application Id is mandatory");
        }
        Optional<JobApplication> jobApplication = jobApplicationRepository.findById(applicationId);
        if(jobApplication.isEmpty()){
            throw new BadRequestException("Application not found");
        }
        jobApplication.get().setApplicationStatus("Withdrawn");
        jobApplicationRepository.saveAndFlush(jobApplication.get());
        return "Application Deleted Successfully";
    }

    @Override
    public List<Job> getAllJobsPostedByMe(String userId) throws Exception {

        if(userId == null || userId.isEmpty()){
            throw new BadRequestException("User Id is mandatory");
        }
        List<Job> jobs = jobRepository.findByJobPostedBy(userId);
        if(jobs.isEmpty()){
            throw new BadRequestException("No Jobs found");
        }
        return jobs;
    }

    @Override
    public List<JobApplication> getAllJobsAppliedByMe(String userId) throws Exception {
        if(userId == null || userId.isEmpty()){
            throw new BadRequestException("User Id is mandatory");
        }
        List<JobApplication> jobApplications = jobApplicationRepository.findByUserId(userId);
        if(jobApplications.isEmpty()){
            throw new BadRequestException("No Jobs found");
        }
        return jobApplications;
    }

    @Override
    public List<JobApplication> getAllApplicationsForJob(String jobId) throws Exception {
        if(jobId == null || jobId.isEmpty()){
            throw new BadRequestException("Job Id is mandatory");
        }
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobIdOrderByResumeScoreDesc(jobId);
        if(jobApplications.isEmpty()){
            throw new BadRequestException("No Applications found");
        }
        return jobApplications;
    }

    @Override
    public List<Job> searchJob(String jobTitle) throws Exception {
        if(jobTitle == null || jobTitle.isEmpty()){
            throw new BadRequestException("Job Title is mandatory");
        }
        List<Job> jobs = jobRepository.findByJobTitleContaining(jobTitle);
        return jobs;
    }

    @Override
    public String updateApplication(String applicationId, String summary, int score, String remarks) throws Exception {

        Optional<JobApplication> jobApplicationO = jobApplicationRepository.findById(applicationId);
        if(jobApplicationO.isEmpty()){
            throw new BadRequestException("Application not found");
        }
        JobApplication jobApplication=jobApplicationO.get();
        jobApplication.setResumeSummary(summary);
        jobApplication.setResumeScore(score);
        jobApplication.setResumeRemarks(remarks);
        jobApplicationRepository.saveAndFlush(jobApplication);
        return "Application Updated Successfully";
    }

    @Override
    public String shortListCandidates(String jobId, Long count) throws Exception {
        if(jobId == null || jobId.isEmpty()){
            throw new BadRequestException("Job Id is mandatory");
        }
        if(count == null){
            throw new BadRequestException("Count is mandatory");
        }
        Optional<Job> job = jobRepository.findById(jobId);
        if(job.isEmpty()){
            throw new BadRequestException("Job not found");
        }
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobIdOrderByResumeScoreDesc(jobId);
        if(jobApplications.isEmpty()){
            throw new BadRequestException("No Applications found");
        }
        List<JobApplication> shortListedApplications = jobApplications.stream().limit(count).collect(Collectors.toList());
        for(JobApplication jobApplication:shortListedApplications){
            jobApplication.setApplicationStatus("Under Review");
            Map<String,String> map = new HashMap<>();
            map.put("job_title",job.get().getJobTitle());
            map.put("event_id","shortlisted");
            map.put("application_id",jobApplication.getApplicationId());
            map.put("recipient_name","user-"+jobApplication.getUserId());
            map.put("assessment_link","http://localhost:5000/quiz");
            map.put("email","narendracse560@gmail.com");
            generationService.sendMail(map);
            jobApplicationRepository.saveAndFlush(jobApplication);
        }
        return "Shortlisted Successfully";
    }

    @Override
    public List<Job> fetchLatestJobs() throws Exception {

        Sort sortByPostedOn = Sort.by("jobPostedOn").descending();
        List<Job> jobs = jobRepository.findAll(sortByPostedOn);
        return jobs;
    }
}
