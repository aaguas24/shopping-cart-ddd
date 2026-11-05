package com.tuempresa.shoppingcart.infraestructure.user.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rol_permiso_entity")
public class RolPermisoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rol_usuario_id", nullable = false)
    private RolUsuario rolUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permiso_entity_id", nullable = false)
    private PermisoEntity permisoEntity;
}