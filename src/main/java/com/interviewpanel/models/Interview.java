package com.interviewpanel.models;

import com.interviewpanel.helpers.InterviewStatus;

public class Interview {
    // interview id, interviewer id, candidate id, start time, end time, status
    private int interviewId;
    private int interviewerId;
    private int candidateId;
    private String startTime;
    private String endTime;
    private InterviewStatus status;

    public Interview(int interviewId, int interviewerId, int candidateId, String startTime, String endTime, InterviewStatus status) {
        this.interviewId = interviewId;
        this.interviewerId = interviewerId;
        this.candidateId = candidateId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Interview() {}

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }
}