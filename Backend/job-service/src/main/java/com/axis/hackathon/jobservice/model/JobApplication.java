package com.axis.hackathon.jobservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class JobApplication {

    @Id
    private String applicationId;
    private String jobId;
    private String userId;
    private String applicationStatus;
    private String applicationRemarks;
    private LocalDateTime appliedOn;
    private LocalDateTime updatedOn;
    private String updatedBy;
    private String resumePath;
    private String resumeSummary;
    private int resumeScore;
    private String resumeRemarks;

    public JobApplication() {
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationRemarks() {
        return applicationRemarks;
    }

    public void setApplicationRemarks(String applicationRemarks) {
        this.applicationRemarks = applicationRemarks;
    }

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getResumeSummary() {
        return resumeSummary;
    }

    public void setResumeSummary(String resumeSummary) {
        this.resumeSummary = resumeSummary;
    }

    public int getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(int resumeScore) {
        this.resumeScore = resumeScore;
    }

    public String getResumeRemarks() {
        return resumeRemarks;
    }

    public void setResumeRemarks(String resumeRemarks) {
        this.resumeRemarks = resumeRemarks;
    }

    public JobApplication(String applicationId, String jobId, String userId, String applicationStatus, String applicationRemarks, LocalDateTime appliedOn, LocalDateTime updatedOn, String updatedBy, String resumePath, String resumeSummary, int resumeScore, String resumeRemarks) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.userId = userId;
        this.applicationStatus = applicationStatus;
        this.applicationRemarks = applicationRemarks;
        this.appliedOn = appliedOn;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.resumePath = resumePath;
        this.resumeSummary = resumeSummary;
        this.resumeScore = resumeScore;
        this.resumeRemarks = resumeRemarks;
    }
}
