package com.example.rimacbienestar.Configuracion.Auth.DTOS;

import lombok.Data;

@Data
public class AuthLoginRequest {
    public String email;
    public String password;
}
