package com.interviewpanel.repository;

import com.interviewpanel.models.Interviewer;

import java.util.HashMap;
import java.util.Map;

public class InterviewerRepository {
    private static InterviewerRepository instance;

    private InterviewerRepository() {
    }

    public static InterviewerRepository getInstance() {
        if (instance == null) {
            instance = new InterviewerRepository();
        }
        return instance;
    }

    private Map<Integer, Interviewer> interviewerMap = new HashMap<>();

    public int getInterviewersSize() {
        return interviewerMap.size();
    }

    public void addInterviewer(Interviewer interviewer) {
        interviewerMap.put(interviewer.getInterviewerId(), interviewer);
    }

    public Interviewer getInterviewerById(int interviewerId) {
        return interviewerMap.get(interviewerId);
    }
}
