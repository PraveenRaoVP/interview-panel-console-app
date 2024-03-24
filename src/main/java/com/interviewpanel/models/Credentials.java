package com.interviewpanel.models;

public class Credentials {
    private int credId;
    private String username;
    private String password;

    public Credentials(int credId, String username, String password) {
        this.credId = credId;
        this.username = username;
        this.password = password;
    }

    public Credentials(){}

    public int getCredId() {
        return credId;
    }

    public void setCredId(int credId) {
        this.credId = credId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
