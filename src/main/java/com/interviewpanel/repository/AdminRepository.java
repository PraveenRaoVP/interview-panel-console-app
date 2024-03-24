package com.interviewpanel.repository;

import com.interviewpanel.models.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRepository {
    private static AdminRepository instance;

    private AdminRepository() {
        adminMap.put(1, new Admin(1, "admin", "admin@gmail.com", "8610571051"));
    }

    public static AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }
        return instance;
    }

    private Map<Integer, Admin> adminMap = new HashMap<>();

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
}
