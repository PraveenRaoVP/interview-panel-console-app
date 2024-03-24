package com.interviewpanel.AdminManager;

import com.interviewpanel.models.Admin;
import com.interviewpanel.repository.AdminRepository;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private AdminModel adminModel;

    public AdminView() {
        this.adminModel = new AdminModel(this);
    }

    public void createAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the following details to create a new admin:");
        System.out.println("Enter the username: ");
        String username = scanner.nextLine();
        System.out.println("Enter the email: ");
        String email = scanner.nextLine();
        System.out.println("Enter the phone number: ");
        String phone = scanner.nextLine();
        adminModel.createAdmin(username, email, phone);
        System.out.println("Admin created successfully.");
    }

    public void removeAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the admin id to remove: ");
        int adminId = scanner.nextInt();

    }

    public void viewAdmins() {
        List<Admin> admins = adminModel.getAllAdmins();
        System.out.println("Admins:");
        System.out.println("ID\tUsername\tEmail\tPhone\tNoOfPanels");
        for(Admin admin : admins) {
            System.out.print(admin.getAdminId() + "\t" + admin.getUserName() + "\t" + admin.getAdminEmail() + "\t" + admin.getAdminPhone() + "\t");
            List<Integer> adminPanels = AdminRepository.getInstance().getPanelsByAdminId(admin.getAdminId());
            if(adminPanels != null)
                System.out.println(adminPanels.size());
            else
                System.out.println(0);
        }
    }

    public void changePassword() {
        Admin admin = adminModel.getCurrentAdmin();
        System.out.println("Do you wish to change your password, " + admin.getUserName() + "? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("y")) {
            System.out.println("Enter the old password: ");
            String oldPassword = scanner.nextLine();
            if(adminModel.verifyOldPassword(oldPassword)) {
                System.out.println("Enter the new password: ");
                String newPassword = scanner.nextLine();
                System.out.println("Re-enter the new password: ");
                String reEnteredPassword = scanner.nextLine();
                if(newPassword.equals(reEnteredPassword)) {
                    adminModel.changePassword(newPassword);
                    System.out.println("Password changed successfully.");
                } else {
                    System.out.println("Passwords do not match. Exiting process.");
                }
            } else {
                System.out.println("Incorrect password. Exiting process.");
            }
        }
    }
}
