package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.InterviewPanel;

import java.io.File;
import java.util.*;

public class InterviewPanelRepository {
    private static InterviewPanelRepository interviewPanelRepository;

    private InterviewPanelRepository() {
    }

    public static InterviewPanelRepository getInstance() {
        if (interviewPanelRepository == null) {
            interviewPanelRepository = new InterviewPanelRepository();
        }
        return interviewPanelRepository;
    }

    private Map<Integer, InterviewPanel> interviewPanels = new HashMap<>();

    public void pushInterviewPanel(InterviewPanel interviewPanel) {
        interviewPanels.put(interviewPanel.getPanelId(), interviewPanel);
    }

    public int getInterviewPanelsSize() {
        return interviewPanels.size();
    }

    public void addInterviewPanel(int interviewerId) {
        int panelId = interviewPanels.size() + 1;
        InterviewPanel interviewPanel = new InterviewPanel(panelId, interviewerId, new LinkedList<>());
        interviewPanels.put(panelId, interviewPanel);
        AdminToInterviewPanel.getInstance().addAdminToInterviewPanel(CacheMemory.getInstance().getCurrentAdminId(), panelId);
    }

    public InterviewPanel getInterviewPanelByID(int panelId) {
        return interviewPanels.get(panelId);
    }

    public void removeInterviewPanel(int panelId) {
        interviewPanels.remove(panelId);
    }

    public List<InterviewPanel> getInterviewPanelsByAdminId(int adminId) {
        List<InterviewPanel> interviewPanels = new ArrayList<>();
        List<Integer> interviewPanelIds = AdminToInterviewPanel.getInstance().getInterviewPanelsByAdminId(CacheMemory.getInstance().getCurrentAdminId());
        if(interviewPanelIds == null) {
            return null;
        }
        for (int panelId : interviewPanelIds) {
            InterviewPanel interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelByID(panelId);
            interviewPanels.add(interviewPanel);
        }
        return interviewPanels;
    }

    private String fileNamePath = "./src/main/resources/interviewpanels.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, interviewPanels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                interviewPanels.clear();
                interviewPanels.putAll(mapper.readValue(file, new TypeReference<Map<Integer, InterviewPanel>>(){}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admintointerviewpanels.json does not exist.");
        }
    }
}
