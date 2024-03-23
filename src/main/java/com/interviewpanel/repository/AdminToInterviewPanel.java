package com.interviewpanel.repository;

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
}
