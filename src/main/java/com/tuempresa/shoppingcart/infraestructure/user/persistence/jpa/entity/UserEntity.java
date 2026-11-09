package com.tuempresa.shoppingcart.infraestructure.user.persistence.jpa.entity;

import com.tuempresa.shoppingcart.infraestructure.customer.persistence.jpa.entity.DireccionEntity;
import com.tuempresa.shoppingcart.shared.enums.EstadoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;

    @Column(name = "primer_apellido", length = 50)
    private String primerApellido;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "email", length = 70)
    private String email;

    @Column(name = "password_hash", length = 50)
    private String passwordHash;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoCliente estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    private void prePersist(){
        this.fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }

}