package com.axis.hackathon.jobservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Job {

    @Id
    private String jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private String jobType;
    private String jobCategory;
    private String jobStatus;
    private String jobPostedBy;
    private LocalDateTime jobPostedOn;
    private LocalDateTime applicationDeadline;
    private int yearsOfExperience;

    List<String> skills;

    public Job() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobPostedBy() {
        return jobPostedBy;
    }

    public void setJobPostedBy(String jobPostedBy) {
        this.jobPostedBy = jobPostedBy;
    }

    public LocalDateTime getJobPostedOn() {
        return jobPostedOn;
    }

    public void setJobPostedOn(LocalDateTime jobPostedOn) {
        this.jobPostedOn = jobPostedOn;
    }

    public LocalDateTime getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDateTime applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Job(String jobId, String jobTitle, String jobDescription, String jobLocation, String jobType, String jobCategory, String jobStatus, String jobPostedBy, LocalDateTime jobPostedOn, LocalDateTime applicationDeadline, int yearsOfExperience, List<String> skills) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobCategory = jobCategory;
        this.jobStatus = jobStatus;
        this.jobPostedBy = jobPostedBy;
        this.jobPostedOn = jobPostedOn;
        this.applicationDeadline = applicationDeadline;
        this.yearsOfExperience = yearsOfExperience;
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
