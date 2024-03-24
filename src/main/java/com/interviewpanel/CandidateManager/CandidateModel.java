package com.interviewpanel.CandidateManager;

import com.interviewpanel.helpers.InterviewStatus;
import com.interviewpanel.helpers.PrintersAndFormatters;
import com.interviewpanel.models.Candidate;
import com.interviewpanel.models.Interview;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.repository.CacheMemory;
import com.interviewpanel.repository.CandidateRepository;
import com.interviewpanel.repository.InterviewPanelRepository;
import com.interviewpanel.repository.InterviewRepository;

import java.util.List;

class CandidateModel {
    public CandidateView candidateView;

    public CandidateModel(CandidateView candidateView) {
        this.candidateView = candidateView;
    }

    public void addCandidate(String name, String email, String phone, String position, String skills, String address, int interviewerId) {
        int candidateId = CandidateRepository.getInstance().getCandidatesSize() + 1;
        Candidate candidate = new Candidate(candidateId, name, email, phone, position, skills, address);
        CandidateRepository.getInstance().pushCandidate(candidate);

        List<InterviewPanel> interviewPanels = InterviewPanelRepository.getInstance().getInterviewPanelsByAdminId(CacheMemory.getInstance().getCurrentAdminId());
        if(interviewerId == -1) {
            InterviewPanel interviewPanel = interviewPanels.get(0);
            for(InterviewPanel panel: interviewPanels) {
                if(panel.getInterviews().size() < interviewPanel.getInterviews().size()) {
                    interviewPanel = panel;
                }
            }
            interviewerId = interviewPanel.getInterviewerId();
            int interviewId = InterviewRepository.getInstance().getInterviewsSize() + 1;
            Interview interview = new Interview(interviewId, interviewerId, candidateId, null, null, InterviewStatus.WAITING);
            if(interviewPanel.getInterviews().isEmpty())  {
                interview.setStatus(InterviewStatus.IN_PROGRESS);
                interview.setStartTime(CacheMemory.getInstance().captureCurrentTime());
            }
            interviewPanel.getInterviews().add(interview);
            InterviewRepository.getInstance().addInterview(interview);
            PrintersAndFormatters.showMessage("Adding Candidate...");
            CandidateRepository.getInstance().pushDataToJSON();
            InterviewRepository.getInstance().pushDataToJSON();
            InterviewPanelRepository.getInstance().pushDataToJSON();
            PrintersAndFormatters.showMessage("Candidate added successfully");
        } else {
            InterviewPanel interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelByID(interviewerId);
            int interviewId = InterviewRepository.getInstance().getInterviewsSize() + 1;
            Interview interview = new Interview(interviewId, interviewerId, candidateId, CacheMemory.getInstance().captureCurrentTime(), null, InterviewStatus.WAITING);
            if(interviewPanel.getInterviews().isEmpty())  {
                interview.setStatus(InterviewStatus.IN_PROGRESS);
            }
            interviewPanel.getInterviews().add(interview);
            PrintersAndFormatters.showMessage("Adding Candidate...");
            CandidateRepository.getInstance().pushDataToJSON();
            InterviewRepository.getInstance().pushDataToJSON();
            InterviewPanelRepository.getInstance().pushDataToJSON();
            PrintersAndFormatters.showMessage("Candidate added successfully");
        }
    }

    public Candidate viewCandidate(int candidateId) {
        CandidateRepository.getInstance().pullDataFromJSON();
        return CandidateRepository.getInstance().getCandidateByID(candidateId);
    }

    public InterviewStatus getInterviewStatusByCandidateId(int candidateId) {
        InterviewRepository.getInstance().pullDataFromJSON();
        CandidateRepository.getInstance().pullDataFromJSON();
        return InterviewRepository.getInstance().getInterviewStatusByCandidateId(candidateId);
    }

    public void removeCandidate(int candidateId) {
        CandidateRepository.getInstance().pullDataFromJSON();
        InterviewRepository.getInstance().pullDataFromJSON();
        CandidateRepository.getInstance().removeCandidateByID(candidateId);
        InterviewRepository.getInstance().removeInterviewByCandidateId(candidateId);
        PrintersAndFormatters.showMessage("Removing Candidate...");
        CandidateRepository.getInstance().pushDataToJSON();
        InterviewRepository.getInstance().pushDataToJSON();
        PrintersAndFormatters.showMessage("Candidate removed successfully");
    }

    public List<Candidate> searchCandidate(String name) {
        CandidateRepository.getInstance().pullDataFromJSON();
        if(name == null || name.isEmpty()) {
            return CandidateRepository.getInstance().getCandidates();
        }
        if(CandidateRepository.getInstance().searchCandidateByName(name).isEmpty()) {
            return CandidateRepository.getInstance().getCandidates();
        }

        return CandidateRepository.getInstance().searchCandidateByName(name);
    }

    public void changeResultOfCandidate(int candidateId, InterviewStatus result) {
        InterviewRepository.getInstance().pullDataFromJSON();
        Interview interview = InterviewRepository.getInstance().getInterviewByCandidateId(candidateId);
        if(interview != null) {
            interview.setStatus(result);
            InterviewRepository.getInstance().addInterview(interview);
        } else {
            PrintersAndFormatters.showMessage("No interview found for the candidate");
            return;
        }
        PrintersAndFormatters.showMessage("Changing result...");
        InterviewRepository.getInstance().pushDataToJSON();
        PrintersAndFormatters.showMessage("Result changed successfully");
    }
}
