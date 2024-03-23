package com.interviewpanel.models;

public class Candidate {
    // candidateID, name, email, phone, positionInterviewing, skills, address
    private int candidateId;
    private String name;
    private String email;
    private String phone;
    private String positionInterviewing;
    private String skills;
    private String address;

    public Candidate(int candidateId, String name, String email, String phone, String positionInterviewing, String skills, String address) {
        this.candidateId = candidateId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.positionInterviewing = positionInterviewing;
        this.skills = skills;
        this.address = address;
    }

    public Candidate() {
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPositionInterviewing() {
        return positionInterviewing;
    }

    public void setPositionInterviewing(String positionInterviewing) {
        this.positionInterviewing = positionInterviewing;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
