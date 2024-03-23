package com.interviewpanel.repository;

import com.interviewpanel.models.Admin;

import java.util.HashMap;
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
}
