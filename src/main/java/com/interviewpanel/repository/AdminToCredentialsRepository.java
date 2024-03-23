package com.interviewpanel.repository;

import java.util.HashMap;
import java.util.Map;

public class AdminToCredentialsRepository {
    private Map<Integer, Integer> adminIdToCredId = new HashMap<>();

    private static AdminToCredentialsRepository instance;
    private AdminToCredentialsRepository() {
        adminIdToCredId.put(1, 1);
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
}
