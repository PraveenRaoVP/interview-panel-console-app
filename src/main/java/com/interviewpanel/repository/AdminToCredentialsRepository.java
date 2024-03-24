package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminToCredentialsRepository {
    private Map<Integer, Integer> adminIdToCredId = new HashMap<>();

    private static AdminToCredentialsRepository instance;
    private AdminToCredentialsRepository() {
        //adminIdToCredId.put(1, 1);
    }

    public static AdminToCredentialsRepository getInstance() {
        if (instance == null) {
            instance = new AdminToCredentialsRepository();
        }
        return instance;
    }

    public void addAdminToCredentials(int adminId, int credId) {
        adminIdToCredId.put(adminId, credId);
    }

    public int getCredIdByAdminId(int adminId) {
        return adminIdToCredId.get(adminId);
    }

    public int getAdminIdByCredId(int credId) {
        for (Map.Entry<Integer, Integer> entry : adminIdToCredId.entrySet()) {
            if (entry.getValue() == credId) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private String fileNamePath = "./src/main/resources/admintocredentials.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, adminIdToCredId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                adminIdToCredId.clear();
                adminIdToCredId.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Integer>>(){}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admintocredentials.json File does not exist.");
        }
    }
}
