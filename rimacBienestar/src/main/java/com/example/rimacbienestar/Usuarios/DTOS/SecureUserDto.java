package com.example.rimacbienestar.Usuarios.DTOS;

import lombok.Data;

@Data
public class SecureUserDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
}