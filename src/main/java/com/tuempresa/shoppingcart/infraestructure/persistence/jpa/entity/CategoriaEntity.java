package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column()
    private String nombre;

    @Column()
    private String descripcion;

    @OneToMany(mappedBy = "categoria", orphanRemoval = true)
    private List<ProductoEntity> producto = new ArrayList<>();

}