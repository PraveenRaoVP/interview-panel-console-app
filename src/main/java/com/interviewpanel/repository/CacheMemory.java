package com.interviewpanel.repository;

public class CacheMemory {
    private static CacheMemory instance;

    private CacheMemory() {
    }

    public static CacheMemory getInstance() {
        if (instance == null) {
            instance = new CacheMemory();
        }
        return instance;
    }

    private int currentAdminId;

    public int getCurrentAdminId() {
        return currentAdminId;
    }

    public void setCurrentAdminId(int currentAdminId) {
        this.currentAdminId = currentAdminId;
    }

    public String captureCurrentTime() {
        return java.time.LocalDateTime.now().toString();
    }

    public void pushAllDataToJSON() {
        AdminRepository.getInstance().pushDataToJSON();
        AdminToCredentialsRepository.getInstance().pushDataToJSON();
        AdminToInterviewPanel.getInstance().pushDataToJSON();
        CandidateRepository.getInstance().pushDataToJSON();
        CredentialsRepository.getInstance().pushDataToJSON();
        InterviewerRepository.getInstance().pushDataToJSON();
        InterviewPanelRepository.getInstance().pushDataToJSON();
        InterviewRepository.getInstance().pushDataToJSON();
    }
}
