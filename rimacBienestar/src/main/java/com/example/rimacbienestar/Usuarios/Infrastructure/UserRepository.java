package com.example.rimacbienestar.Usuarios.Infrastructure;

import com.example.rimacbienestar.Usuarios.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByDni(Integer dni);
    Optional<User> findByEmail(String email);
}