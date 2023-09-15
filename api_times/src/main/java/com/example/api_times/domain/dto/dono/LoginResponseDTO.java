package com.example.api_times.domain.dto.dono;


public class LoginResponseDTO {
    private String token;
    private DonoResponseDTO dono;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public DonoResponseDTO getTreinador() {
        return dono;
    }
    public void setTreinador(DonoResponseDTO dono) {
        this.dono = dono;
    }
}