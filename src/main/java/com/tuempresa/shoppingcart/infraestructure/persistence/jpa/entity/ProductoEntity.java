package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity;

import com.tuempresa.shoppingcart.domain.catalog.valueobject.Precio;
import com.tuempresa.shoppingcart.shared.enums.EstadoProducto;
import com.tuempresa.shoppingcart.shared.enums.Moneda;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProducto estado;

    @Column()
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column()
    private Moneda moneda;

    @Column
    private String sku;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @OneToMany(mappedBy = "producto", orphanRemoval = true)
    private List<InventarioEntity> inventario;

    public ProductoEntity(UUID id, String nombre, String descripcion, EstadoProducto estado, BigDecimal precio, Moneda moneda, String sku, CategoriaEntity categoria, List<InventarioEntity> inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precio = precio;
        this.moneda = moneda;
        this.sku = sku;
        this.categoria = categoria;
        this.inventario = inventario;
    }

    public ProductoEntity() {
    }
}