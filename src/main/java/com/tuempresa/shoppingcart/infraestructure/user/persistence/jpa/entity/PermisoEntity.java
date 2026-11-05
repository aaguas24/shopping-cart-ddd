package com.tuempresa.shoppingcart.infraestructure.user.persistence.jpa.entity;

import com.tuempresa.shoppingcart.shared.enums.CodigoPermisoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "permiso_entity")
public class PermisoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "codigo")
    private CodigoPermisoEnum codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "permisoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolPermisoEntity> rolPermisoEntities = new LinkedHashSet<>();

}