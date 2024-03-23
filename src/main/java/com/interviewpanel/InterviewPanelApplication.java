package com.interviewpanel;

import com.interviewpanel.auth.LoginView;

public class InterviewPanelApplication {

    private static InterviewPanelApplication instance;
    private InterviewPanelApplication() {
    }

    public static InterviewPanelApplication getInstance() {
        if(instance == null) {
            instance = new InterviewPanelApplication();
        }
        return instance;
    }

    private void create() {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    private static String appName = "Interview Panel Application.";
    private static String version = "1.0.0";

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return version;
    }


    public static void main(String[] args) {
        InterviewPanelApplication.getInstance().create();
    }
}