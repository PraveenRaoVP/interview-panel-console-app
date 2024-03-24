package com.interviewpanel.AdminManager;

import com.interviewpanel.models.Admin;
import com.interviewpanel.models.Credentials;
import com.interviewpanel.repository.AdminRepository;
import com.interviewpanel.repository.AdminToCredentialsRepository;
import com.interviewpanel.repository.CacheMemory;
import com.interviewpanel.repository.CredentialsRepository;

import java.util.List;

public class AdminModel {
    private AdminView adminView;

    public AdminModel(AdminView adminView) {
        this.adminView = adminView;
    }

    public void createAdmin(String username, String email, String phone) {
        // create a new admin
        int adminId = AdminRepository.getInstance().getAdminSize() + 1;
        AdminRepository.getInstance().pushAdmin(new Admin(adminId, username, email, phone));
        CredentialsRepository.getInstance().createNewCredentials(username, "admin");
        int credId = CredentialsRepository.getInstance().getCredentialsIdByUsername(username);
        AdminToCredentialsRepository.getInstance().addAdminToCredentials(adminId, credId);
    }

    public List<Admin> getAllAdmins() {
        return AdminRepository.getInstance().getAllAdmins();
    }

    public Credentials getCredentialsByAdminId(int adminId) {
        int credId = AdminToCredentialsRepository.getInstance().getCredIdByAdminId(adminId);
        return CredentialsRepository.getInstance().getCredentialsById(credId);
    }

    public Admin getCurrentAdmin() {
        return AdminRepository.getInstance().getAdminById(CacheMemory.getInstance().getCurrentAdminId());
    }

    public boolean verifyOldPassword(String oldPassword) {
        Admin admin = getCurrentAdmin();
        Credentials credentials = getCredentialsByAdminId(admin.getAdminId());
        return credentials.getPassword().equals(oldPassword);
    }

    public void changePassword(String newPassword) {
        Admin admin = getCurrentAdmin();
        Credentials credentials = getCredentialsByAdminId(admin.getAdminId());
        credentials.setPassword(newPassword);
    }
}
