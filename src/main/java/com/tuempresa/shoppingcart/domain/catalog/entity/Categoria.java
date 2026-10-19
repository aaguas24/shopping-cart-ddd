package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Categoria extends AggregateRoot {
    private final String nombre;
    private final String descripcion;
    private final UUID idProducto;

    public Categoria(UUID id, String nombre, String descripcion, UUID idProducto) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idProducto=" + idProducto +
                '}';
    }
}
