package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminToInterviewPanel {
    private static AdminToInterviewPanel instance;

    private AdminToInterviewPanel() {
    }

    public static AdminToInterviewPanel getInstance() {
        if (instance == null) {
            instance = new AdminToInterviewPanel();
        }
        return instance;
    }

    private Map<Integer, List<Integer>> adminIdToInterviewPanelId = new HashMap<>();

    public List<Integer> getInterviewPanelsByAdminId(int adminId) {
        return adminIdToInterviewPanelId.get(adminId);
    }
    public void addAdminToInterviewPanel(int adminId, int interviewPanelId) {
        if(adminIdToInterviewPanelId.containsKey(adminId)) {
            adminIdToInterviewPanelId.get(adminId).add(interviewPanelId);
        } else {
            List<Integer> interviewPanelIds = new ArrayList<>();
            interviewPanelIds.add(interviewPanelId);
            adminIdToInterviewPanelId.put(adminId, interviewPanelIds);
        }
    }

    private String fileNamePath = "./src/main/resources/admintointerviewpanels.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, adminIdToInterviewPanelId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                adminIdToInterviewPanelId.clear();
                adminIdToInterviewPanelId.putAll(mapper.readValue(file, new TypeReference<Map<Integer, List<Integer>>>(){}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admintointerviewpanels.json does not exist.");
        }
    }
}
