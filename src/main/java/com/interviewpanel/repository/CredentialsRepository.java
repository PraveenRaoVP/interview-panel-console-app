package com.interviewpanel.repository;

import com.interviewpanel.models.Credentials;

import java.util.HashMap;
import java.util.Map;

public class CredentialsRepository {

    private Map<Integer, Credentials> credentialsMap = new HashMap<>();
    private static CredentialsRepository instance;

    private CredentialsRepository() {
        credentialsMap.put(1, new Credentials(1, "admin", "admin"));
    }

    public static CredentialsRepository getInstance() {
        if(instance == null) {
            instance = new CredentialsRepository();
        }
        return instance;
    }

    public boolean checkIfUsernameExists(String username) {
        for(Credentials credentials : credentialsMap.values()) {
            if(credentials.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean authenticatePassword(String username, String password) {
        for(Credentials credentials : credentialsMap.values()) {
            if(credentials.getUsername().equals(username) && credentials.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public int getCredentialsIdByUsername(String username) {
        for(Credentials credentials : credentialsMap.values()) {
            if(credentials.getUsername().equals(username)) {
                return credentials.getCredId();
            }
        }
        return -1;
    }
}
