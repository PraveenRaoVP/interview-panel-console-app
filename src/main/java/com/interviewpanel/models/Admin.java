package com.interviewpanel.models;

public class Admin {
    private int adminId;

    private String userName;
    private String adminEmail;
    private String adminPhone;

    public Admin(int adminId, String userName, String adminEmail, String adminPhone) {
        this.adminId = adminId;
        this.userName = userName;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }
}
