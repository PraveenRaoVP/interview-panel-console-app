package com.interviewpanel.auth;

import com.interviewpanel.helpers.PrintersAndFormatters;
import com.interviewpanel.repository.AdminRepository;
import com.interviewpanel.repository.AdminToCredentialsRepository;
import com.interviewpanel.repository.CacheMemory;
import com.interviewpanel.repository.CredentialsRepository;

class LoginModel {

    private final LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void initLogin() {
        int attempts = 0;
        do {
            loginView.loginDetails();
        } while(++attempts < 3);
        PrintersAndFormatters.showMessage("Too many attempts. Exiting...");
        System.exit(0);
    }

    public boolean authenticateUser(String username, String password) {
        CredentialsRepository.getInstance().pullDataFromJSON();
        AdminRepository.getInstance().pullDataFromJSON();
        AdminToCredentialsRepository.getInstance().pullDataFromJSON();
        if(CredentialsRepository.getInstance().checkIfUsernameExists(username)) {
            if(CredentialsRepository.getInstance().authenticatePassword(username, password)) {
                int credentialsId = CredentialsRepository.getInstance().getCredentialsIdByUsername(username);
                CacheMemory.getInstance().setCurrentAdminId(AdminToCredentialsRepository.getInstance().getAdminIdByCredId(credentialsId));
                return true;
            } else {
                PrintersAndFormatters.showMessage("Incorrect password");
            }
        } else {
            PrintersAndFormatters.showMessage("Username not found");
        }
        return false;
    }
}
