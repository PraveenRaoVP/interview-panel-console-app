package com.interviewpanel.CandidateManager;

import com.interviewpanel.InterviewPanelManager.InterviewPanelView;
import com.interviewpanel.helpers.InterviewStatus;
import com.interviewpanel.models.Candidate;
import com.interviewpanel.repository.CacheMemory;
import com.interviewpanel.repository.CandidateRepository;
import com.interviewpanel.repository.InterviewRepository;

import java.util.List;
import java.util.Scanner;

public class CandidateView {
    public CandidateModel candidateModel;

    public CandidateView() {
        this.candidateModel = new CandidateModel(this);
    }

    public void addCandidateToPanel() {
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the details of the candidate:-");
        System.out.print("Enter the name of the candidate: ");
        String name = scanner.nextLine();
        System.out.print("Enter the email of the candidate: ");
        String email = scanner.nextLine();
        System.out.print("Enter the phone number of the candidate: ");
        String phone = scanner.nextLine();
        System.out.print("Enter the position the candidate is interviewing for: ");
        String position = scanner.nextLine();
        System.out.print("Enter the skills seperated by commas(,): ");
        String skills = scanner.nextLine();
        System.out.println("Enter the address of the employee: ");
        String address = scanner.nextLine();

        System.out.println("Do you want to assign the candidate yourself(y) or assign automatically to an interviewer(n)?");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("y")) {
            InterviewPanelView interviewPanelView = new InterviewPanelView();
            interviewPanelView.viewInterviewPanel();

            System.out.println("Enter the interviewer id: ");
            int interviewerId = scanner.nextInt();
            candidateModel.addCandidate(name, email, phone, position, skills, address, interviewerId);
        } else {
            candidateModel.addCandidate(name, email, phone, position, skills, address, -1);
        }
        System.out.println("Candidate added successfully");
    }

    public void removeCandidateFromPanel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate id: ");
        int candidateId = scanner.nextInt();
        candidateModel.removeCandidate(candidateId);
        System.out.println("Candidate removed successfully");
    }



    public void changeResultOfCandidate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate name: ");
        String name = scanner.nextLine();
        List<Candidate> candidates = candidateModel.searchCandidate(name);
        if(candidates.isEmpty()) {
            System.out.println("No candidate found with the given name");
            return;
        } else {
            System.out.println("Candidates found with the given name:-");
            System.out.println("Candidate ID \t Candidate Name \t Email \t Phone");
            for(Candidate candidate: candidates) {
                System.out.println(candidate.getCandidateId() + "\t" + candidate.getName() + "\t" + candidate.getEmail() + "\t" + candidate.getPhone());
            }
        }
        System.out.println("Enter the candidate id: ");
        int candidateId = scanner.nextInt();
        Candidate candidate = candidateModel.viewCandidate(candidateId);
        if(InterviewRepository.getInstance().getInterviewStatusByCandidateId(candidateId) == InterviewStatus.WAITING || InterviewRepository.getInstance().getInterviewStatusByCandidateId(candidateId) == InterviewStatus.IN_PROGRESS) {
            System.out.println("Candidate is in the queue or in progress. Do you want to change the result?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            if(choice == 2) {
                return;
            }
        }
        System.out.println("1. SELECTED");
        System.out.println("2. REJECTED");
        System.out.println("Enter the result of the candidate: ");
        int result = scanner.nextInt();
        if(result == 1) {
            candidateModel.changeResultOfCandidate(candidateId, InterviewStatus.SELECTED);
        } else if(result == 2) {
            candidateModel.changeResultOfCandidate(candidateId, InterviewStatus.REJECTED);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public void viewCandidateDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate id: ");
        int candidateId = scanner.nextInt();
        Candidate candidate = candidateModel.viewCandidate(candidateId);
        if(candidate != null) {
            System.out.println("Candidate Details:-");
            System.out.println("Name: " + candidate.getName());
            System.out.println("Email: " + candidate.getEmail());
            System.out.println("Phone: " + candidate.getPhone());
            System.out.println("Position: " + candidate.getPositionInterviewing());
            System.out.println("Skills: " + candidate.getSkills());
            System.out.println("Address: " + candidate.getAddress());
            System.out.print("Interview Status: ");
            System.out.println(candidateModel.getInterviewStatusByCandidateId(candidateId));
        } else {
            System.out.println("No candidate found with the given id");
        }
    }
}
