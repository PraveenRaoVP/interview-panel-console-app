package com.interviewpanel.repository;

import com.interviewpanel.models.InterviewPanel;

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
}
