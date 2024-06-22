package com.example.rimacbienestar.Configuracion.Auth;

import com.example.rimacbienestar.Configuracion.Autentificacion.JwtService;
import com.example.rimacbienestar.Configuracion.Auth.DTOS.AuthLoginRequest;
import com.example.rimacbienestar.Configuracion.Auth.DTOS.AuthRegisterRequest;
import com.example.rimacbienestar.Configuracion.Auth.DTOS.AuthResponse;
import com.example.rimacbienestar.Exceptions.UserAlreadyExistException;
import com.example.rimacbienestar.Usuarios.Domain.Role;
import com.example.rimacbienestar.Usuarios.Domain.User;
import com.example.rimacbienestar.Usuarios.Infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    final UserRepository userRepository;
    final JwtService jwtService;
    final PasswordEncoder passwordEncoder;
    final ModelMapper modelMapper;

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    public AuthResponse login(AuthLoginRequest req) {
        Optional<User> user = userRepository.findByEmail(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        AuthResponse response = new AuthResponse();

        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public AuthResponse register(AuthRegisterRequest req) {
        Optional<User> user = userRepository.findByEmail(req.getEmail());
        if (user.isPresent()) throw new UserAlreadyExistException("Email is already registered");

        User newUser = modelMapper.map(req, User.class);
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());

        if(req.getIsAdmin()) newUser.setRole(Role.ADMIN);
        else newUser.setRole(Role.USER);

        userRepository.save(newUser);

        AuthResponse response = new AuthResponse();
        response.setToken(jwtService.generateToken(newUser));
        return response;
    }

}