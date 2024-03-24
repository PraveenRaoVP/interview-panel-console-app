package com.interviewpanel.MainMenuManager;

import com.interviewpanel.CandidateManager.CandidateView;
import com.interviewpanel.InterviewPanelManager.InterviewPanelView;
import com.interviewpanel.repository.CacheMemory;

class MainMenuModel {
    public MainMenuView mainMenuView;

    public MainMenuModel(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }
    InterviewPanelView interviewPanelView = new InterviewPanelView();
    CandidateView candidateView = new CandidateView();
//    AdminView adminView = new AdminView();
    public void handleLogout() {
        CacheMemory.getInstance().setCurrentAdminId(0);
        CacheMemory.getInstance().pushAllDataToJSON();
    }
}
