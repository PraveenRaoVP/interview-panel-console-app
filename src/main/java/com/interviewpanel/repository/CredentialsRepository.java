package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.interviewpanel.models.Credentials;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CredentialsRepository {

    private Map<Integer, Credentials> credentialsMap = new HashMap<>();
    private static CredentialsRepository instance;

    private CredentialsRepository() {
        // credentialsMap.put(1, new Credentials(1, "admin", "admin"));
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

    public void createNewCredentials(String username, String password) {
        int credId = credentialsMap.size() + 1;
        credentialsMap.put(credId, new Credentials(credId, username, password));
    }

    public Credentials getCredentialsById(int credId) {
        return credentialsMap.get(credId);
    }

    private String fileNamePath = "src/main/resources/credentials.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, credentialsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                credentialsMap.clear();
                credentialsMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Credentials>>(){}));
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("credentials.json File does not exist");
        }
    }
}
