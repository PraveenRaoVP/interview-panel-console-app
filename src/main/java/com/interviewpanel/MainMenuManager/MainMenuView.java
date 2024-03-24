package com.interviewpanel.MainMenuManager;

import com.interviewpanel.AdminManager.AdminView;
import com.interviewpanel.CandidateManager.CandidateView;
import com.interviewpanel.InterviewPanelManager.InterviewPanelView;
import com.interviewpanel.auth.LoginView;
import com.interviewpanel.helpers.PrintersAndFormatters;
import com.interviewpanel.repository.CandidateRepository;

import java.util.Scanner;

public class MainMenuView {

    public MainMenuModel mainMenuModel;

    public MainMenuView() {
        mainMenuModel = new MainMenuModel(this);
    }

    public void mainMenu() {
        int ch = 0;
        Scanner sc = new Scanner(System.in);
        do {
            displayMainMenu();
            ch = sc.nextInt();
            PrintersAndFormatters.dashLine();
            switch(ch) {
                case 1:
                    handleInterviewPanelOptions();
                    break;
                case 2:
                    handleCandidateOptions();
                    break;
                case 3:
                    handleAdminOptions();
                    break;
                case 4:
                    mainMenuModel.handleLogout();
                    LoginView loginView = new LoginView();
                    System.out.println("Logging out...");
                    loginView.init();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while(ch != 5);
    }

    private void handleInterviewPanelOptions() {
        PrintersAndFormatters.dottedLine();
        System.out.println("Interview Panel Options");
        PrintersAndFormatters.dottedLine();
        System.out.println("1. Create New Interview Panel");
        System.out.println("2. View Interview Panel");
        System.out.println("3. Terminate Current Interview Panel");
        System.out.println("4. Clear Interview Panel");
        System.out.println("5. Clear All Interview Panels");
        System.out.println("6. Remove Interview Panel");
        System.out.println("7. Back to Main Menu");
        System.out.print("Enter your choice: ");

        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        InterviewPanelView interviewPanelView = new InterviewPanelView();
        PrintersAndFormatters.dashLine();
        switch(ch) {
            case 1:
                interviewPanelView.createNewInterviewPanel();
                break;
            case 2:
                interviewPanelView.viewInterviewPanel();
                break;
            case 3:
                interviewPanelView.terminateCurrentInterviewPanel();
                break;
            case 4:
                interviewPanelView.clearInterviewPanel();
                break;
            case 5:
                interviewPanelView.clearAllInterviewPanels();
                break;
            case 6:
                interviewPanelView.removeInterviewPanel();
                break;
            case 7:
                System.out.println("Going Back To Main Menu");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        PrintersAndFormatters.dashLine();
    }

    private void handleCandidateOptions() {
        PrintersAndFormatters.dottedLine();
        System.out.println("Candidate Options");
        PrintersAndFormatters.dottedLine();
        System.out.println("1. Add Candidate to Panel");
        System.out.println("2. Remove Candidate from Panel");
        System.out.println("3. Change Result of Candidate");
        System.out.println("4. View Candidate Details");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        Scanner sc = new Scanner(System.in);

        int ch = sc.nextInt();
        PrintersAndFormatters.dashLine();
        CandidateView candidateView = new CandidateView();
        switch(ch) {
            case 1:
                candidateView.addCandidateToPanel();
                break;
            case 2:
                candidateView.removeCandidateFromPanel();
                break;
            case 3:
                candidateView.changeResultOfCandidate();
                break;
            case 4:
                candidateView.viewCandidateDetails();
                break;
            case 5:
                System.out.println("Going Back To Main Menu");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        PrintersAndFormatters.dashLine();
    }

    private void handleAdminOptions() {
        PrintersAndFormatters.dottedLine();
        System.out.println("Admin Options");
        PrintersAndFormatters.dottedLine();
        System.out.println("1. Add Admin");
        System.out.println("2. Remove Admin");
        System.out.println("3. View Admin");
        System.out.println("4. Change Password");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        AdminView adminView = new AdminView();
        PrintersAndFormatters.dashLine();
        switch(ch) {
            case 1:
                adminView.createAdmin();
                break;
            case 2:
                adminView.removeAdmin();
                break;
            case 3:
                adminView.viewAdmins();
                break;
            case 4:
                adminView.changePassword();
                break;
            case 5:
                System.out.println("Going Back To Main Menu");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        PrintersAndFormatters.dashLine();
    }

    public void displayMainMenu() {
        PrintersAndFormatters.starLine();
        System.out.println("Main Menu");
        PrintersAndFormatters.starLine();
        System.out.println("1. Interview Panel Options");
        System.out.println("2. Candidate Options");
        System.out.println("3. Admin Options");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}
