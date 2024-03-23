package com.interviewpanel.InterviewPanelManager;

import com.interviewpanel.helpers.InterviewStatus;
import com.interviewpanel.helpers.PrintersAndFormatters;
import com.interviewpanel.models.Interview;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.Interviewer;
import com.interviewpanel.repository.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class InterviewPanelModel {
    public InterviewPanelView interviewPanelView;
    public InterviewPanelModel(InterviewPanelView interviewPanelView) {
        this.interviewPanelView = interviewPanelView;
    }

    public void createNewInterviewPanel(String interviewerName, String interviewerEmail, String interviewerPhone, String interviewerDesignation, String interviewerDepartment, String interviewerOrganization) {
        int interviewerId = InterviewerRepository.getInstance().getInterviewersSize() + 1;
        Interviewer interviewer = new Interviewer(interviewerId, interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment, interviewerOrganization);
        InterviewerRepository.getInstance().addInterviewer(interviewer);
        InterviewPanelRepository.getInstance().addInterviewPanel(interviewerId);
    }

    public List<InterviewPanel> getInterviewPanels() {
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
    public boolean checkIfValidPanelId(int panelId) {
        return panelId > 0 && panelId <= InterviewPanelRepository.getInstance().getInterviewPanelsSize();
    }

    public void terminateCurrentInterviewInPanel(int panelId) {
        InterviewPanel interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelByID(panelId);
        if(!interviewPanel.getInterviews().isEmpty()) {
            Interview interview = interviewPanel.getInterviews().poll();
            if(interview != null)
                interview.setStatus(InterviewStatus.UNDER_REVIEW);
            assert interviewPanel.getInterviews().peek() != null;
            if (interviewPanel.getInterviews().peek() != null) {
                interviewPanel.getInterviews().peek().setStatus(InterviewStatus.IN_PROGRESS);
            }
            if (interview != null) {
                System.out.println(interview.getCandidateId() + " is under review");
            }
        } else {
            PrintersAndFormatters.showMessage("No candidates in the panel");
        }
    }

    public void clearInterviewPanel(int panelId) {
        InterviewPanel interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelByID(panelId);
        interviewPanel.getInterviews().clear();
    }


    public void clearAllInterviewPanels() {
        List<InterviewPanel> interviewPanels = getInterviewPanels();
        for (InterviewPanel interviewPanel : interviewPanels) {
            clearInterviewPanel(interviewPanel.getPanelId());
        }

    }

    public void removeInterviewPanel(int panelId) {
        InterviewPanelRepository.getInstance().removeInterviewPanel(panelId);
        PrintersAndFormatters.showMessage("Removing Interview Panel...");

        PrintersAndFormatters.showMessage("Interview Panel removed successfully");
    }
}
