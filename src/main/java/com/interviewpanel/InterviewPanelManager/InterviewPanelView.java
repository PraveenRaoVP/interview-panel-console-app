package com.interviewpanel.InterviewPanelManager;

import com.interviewpanel.helpers.InterviewStatus;
import com.interviewpanel.models.Candidate;
import com.interviewpanel.models.Interview;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.Interviewer;
import com.interviewpanel.repository.CandidateRepository;
import com.interviewpanel.repository.InterviewerRepository;

import java.util.List;
import java.util.Scanner;

public class InterviewPanelView {
    public InterviewPanelModel interviewPanelModel;

    public InterviewPanelView() {
        this.interviewPanelModel = new InterviewPanelModel(this);
    }

    public void createNewInterviewPanel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Interviewer Details:-");
        System.out.println("Enter the name of the interviewer: ");
        String interviewerName = sc.nextLine();
        System.out.println("Enter the email of the interviewer: ");
        String interviewerEmail = sc.nextLine();
        System.out.println("Enter the phone number of the interviewer: ");
        String interviewerPhone = sc.nextLine();
        System.out.println("Enter the designation of the interviewer: ");
        String interviewerDesignation = sc.nextLine();
        System.out.println("Enter the department of the interviewer: ");
        String interviewerDepartment = sc.nextLine();
        System.out.println("Enter the organization of the interviewer: ");
        String interviewerOrganization = sc.nextLine();
        this.interviewPanelModel.createNewInterviewPanel(interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment, interviewerOrganization);
        System.out.println("New Interview Panel Created for the interviewer: " + interviewerName);
    }

    public void viewInterviewPanel() {
        List<InterviewPanel> interviewPanels = this.interviewPanelModel.getInterviewPanels();
        if (interviewPanels == null) {
            System.out.println("There are no interview panels");
            return;
        }
        System.out.println("Interview Panels:");
        System.out.println("ID\tName\tEmail\tCandidates");
        if(interviewPanels.isEmpty()) {
            System.out.println("No interview panels available");
            return;
        }
        for(InterviewPanel interviewPanel : interviewPanels) {
            Interviewer interviewer = InterviewerRepository.getInstance().getInterviewerById(interviewPanel.getInterviewerId());
            System.out.print(interviewPanel.getPanelId() + "\t" + interviewer.getInterviewerName() + "\t" + interviewer.getInterviewerEmail() + "\t");
            if(interviewPanel.getInterviews().isEmpty()) {
                System.out.println("N/A");
                continue;
            }
            for(Interview interview: interviewPanel.getInterviews()) {
                Candidate candidate = CandidateRepository.getInstance().getCandidateById(interview.getCandidateId());
                if(candidate!=null) { // && interview.getStatus() == InterviewStatus.IN_PROGRESS
                    if(interviewPanel.getInterviews().peek() == interview) {
                        interview.setStatus(InterviewStatus.IN_PROGRESS);
                        System.out.print(candidate.getName()+"(In Progress), ");
                    } else {
                        System.out.print(candidate.getName() + ", ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void terminateCurrentInterviewPanel() {
        Scanner sc = new Scanner(System.in);
        int panelId = getPanelIdFromUser();
        if(!interviewPanelModel.checkIfValidPanelId(panelId)) {
            System.out.println("Invalid Panel ID");
            return;
        }
        interviewPanelModel.terminateCurrentInterviewInPanel(panelId);
    }

    public void clearInterviewPanel() {
        Scanner sc = new Scanner(System.in);
        int panelId = getPanelIdFromUser();
        if(!interviewPanelModel.checkIfValidPanelId(panelId)) {
            System.out.println("Invalid Panel ID");
            return;
        }
        interviewPanelModel.clearInterviewPanel(panelId);
        System.out.println("Panel cleared successfully");
    }

    public void clearAllInterviewPanels() {
        interviewPanelModel.clearAllInterviewPanels();
        System.out.println("Cleared all interview panels successfully.");
    }

    public void removeInterviewPanel() {
        Scanner sc = new Scanner(System.in);
        int panelId = getPanelIdFromUser();
        if(!interviewPanelModel.checkIfValidPanelId(panelId)) {
            System.out.println("Invalid Panel ID");
            return;
        }
        interviewPanelModel.removeInterviewPanel(panelId);
    }

    private int getPanelIdFromUser() {
        System.out.println("Do you want to view the panels created by you? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            viewInterviewPanel();
        }
        System.out.println("Enter the panel ID: ");
        return scanner.nextInt();
    }
}
