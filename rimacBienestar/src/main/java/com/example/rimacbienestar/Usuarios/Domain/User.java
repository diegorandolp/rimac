package com.example.rimacbienestar.Usuarios.Domain;

import com.example.rimacbienestar.MisionesIndividuales.Domain.MisionesIndividuales;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    private Integer dni;
    @Column(name = "role", nullable = false)
    Role role;

    @Column(name = "first_name", nullable = false)
    String firstName;
    @Column(name = "last_name", nullable = false)
    String lastName;
    @Column(name = "age", nullable = false)
    Integer age;
    @Column(name = "email", nullable = false, unique = true)
    String email;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<MisionesIndividuales> misionesIndividuales = new ArrayList<>();

    @Transient
    String role_prefix = "ROLE_";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role_prefix + role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
