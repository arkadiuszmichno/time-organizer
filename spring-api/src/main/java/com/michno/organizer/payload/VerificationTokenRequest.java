package com.michno.organizer.payload;

public class VerificationTokenRequest {
    private String token;

    public VerificationTokenRequest() {
    }

    public VerificationTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
