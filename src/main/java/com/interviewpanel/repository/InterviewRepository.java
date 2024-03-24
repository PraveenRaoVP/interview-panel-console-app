package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.helpers.InterviewStatus;
import com.interviewpanel.models.Interview;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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

    private String fileNamePath = "./src/main/resources/interviews.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, interviewMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                interviewMap.clear();
                interviewMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Interview>>(){}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admintointerviewpanels.json does not exist.");
        }
    }

    public void removeInterviewByCandidateId(int candidateId) {
        for(Interview interview: interviewMap.values()) {
            if (interview.getCandidateId() == candidateId) {
                interviewMap.remove(interview.getInterviewId());
            }
        }
    }
}
