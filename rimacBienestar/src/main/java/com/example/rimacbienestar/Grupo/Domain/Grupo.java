package com.example.rimacbienestar.Grupo.Domain;

import com.example.rimacbienestar.MisionesGrupales.Domain.MisionesGrupales;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "grupo")
    private List<MisionesGrupales> misionesGrupales = new ArrayList<>();


}
