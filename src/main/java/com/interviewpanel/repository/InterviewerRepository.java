package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Interviewer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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

    private String fileNamePath = "./src/main/resources/interviewers.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, interviewerMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                interviewerMap.clear();
                interviewerMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Interviewer>>(){}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admintointerviewpanels.json does not exist.");
        }
    }
}
