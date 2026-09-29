package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Categoria extends AggregateRoot {
    private String nombre;
    private String descripcion;

    public Categoria(UUID id, String nombre, String descripcion) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
