package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Admin;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRepository {
    private static AdminRepository instance;

    private Map<Integer, Admin> adminMap = new HashMap<>();

    private AdminRepository() {
        //adminMap.put(1, new Admin(1, "admin", "admin@gmail.com", "8610571051"));
    }

    public static AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }
        return instance;
    }

    public Admin getAdminById(int adminId) {
        return adminMap.get(adminId);
    }

    public int getAdminSize() {
        return adminMap.size();
    }

    public void pushAdmin(Admin admin) {
        adminMap.put(admin.getAdminId(), admin);
    }

    public List<Admin> getAllAdmins() {
        return adminMap.values().stream().toList();
    }

    public List<Integer> getPanelsByAdminId(int adminId) {
        return AdminToInterviewPanel.getInstance().getInterviewPanelsByAdminId(adminId);
    }

    private String fileNamePath = "./src/main/resources/admins.json";

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, adminMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                adminMap.clear();
                adminMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Admin>>(){}));
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("admins.json File does not exist.");
        }
    }
}
