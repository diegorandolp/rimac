package com.example.rimacbienestar.Misiones.Infrastructure;

import com.example.rimacbienestar.Misiones.Domain.Misiones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseMisionesRepository <T extends Misiones> extends JpaRepository<T,Long> {
}
