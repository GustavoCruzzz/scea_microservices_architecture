package com.ucsal.SpacesService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "academic_spaces")
public class AcademicSpaces {

    @Id
    @SequenceGenerator(name = "spaces_seq", sequenceName = "spaces_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spaces_seq")
    private Long id;

    @Column(name = "space_name", nullable = false, length = 100)
    private String name;

    // Tipo de espaço: SALA, LAB ou AUDITORIO
    @Column(name = "space_type", nullable = false, length = 20)
    private String spaceType;

    @Column(name = "has_computer", nullable = false)
    private boolean hasComputer = false;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    // Motivo da desativação quando active = false (opcional)
    @Column(name = "disable_reason", length = 500)
    private String disableReason;
}
