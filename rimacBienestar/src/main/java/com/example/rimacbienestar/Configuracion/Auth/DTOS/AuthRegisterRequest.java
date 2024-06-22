package com.example.rimacbienestar.Configuracion.Auth.DTOS;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    String firstName;
    String lastName;
    Integer age;
    Integer dni;
    String email;
    String password;
    Boolean isAdmin;
}
