package com.interviewpanel.repository;

import com.interviewpanel.helpers.InterviewStatus;
import com.interviewpanel.models.Interview;

import java.util.HashMap;
import java.util.Map;

public class InterviewRepository {
    private static InterviewRepository instance;

    private InterviewRepository() {
    }

    public static InterviewRepository getInstance() {
        if (instance == null) {
            instance = new InterviewRepository();
        }
        return instance;
    }

    private Map<Integer, Interview> interviewMap = new HashMap<>();

    public int getInterviewsSize() {
        return interviewMap.size();
    }

    public InterviewStatus getInterviewStatusByCandidateId(int candidateId) {
        for(Interview interview: interviewMap.values()) {
            if(interview.getCandidateId() == candidateId) {
                return interview.getStatus();
            }
        }
        return null;
    }

    public void addInterview(Interview interview) {
        interviewMap.put(interview.getInterviewId(), interview);
    }

    public Interview getInterviewByCandidateId(int candidateId) {
        for(Interview interview: interviewMap.values()) {
            if(interview.getCandidateId() == candidateId) {
                return interview;
            }
        }
        return null;
    }
}
