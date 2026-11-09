package com.tuempresa.shoppingcart.infraestructure.user.persistence.jpa.entity;

import com.tuempresa.shoppingcart.shared.enums.RolUsuarioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "rol_usuario")
public class RolUsuario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private RolUsuarioEnum nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "rolUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolPermisoEntity> rolPermisoEntities = new LinkedHashSet<>();

}