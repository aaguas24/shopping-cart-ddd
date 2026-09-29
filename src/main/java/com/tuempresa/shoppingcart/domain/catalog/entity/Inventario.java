package com.tuempresa.shoppingcart.domain.catalog.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Inventario {
    private Integer cantidadDisponible;
    private Integer stock;
    private UUID idProducto;

    public Inventario(Integer cantidadDisponible, Integer stock, UUID idProducto) {
        this.cantidadDisponible = cantidadDisponible;
        this.stock = stock;
        this.idProducto = idProducto;
    }
}
