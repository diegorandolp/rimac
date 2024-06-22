package com.example.rimacbienestar.Misiones.Domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "misiones")
@Inheritance(strategy = InheritanceType.JOINED)
public class Misiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
