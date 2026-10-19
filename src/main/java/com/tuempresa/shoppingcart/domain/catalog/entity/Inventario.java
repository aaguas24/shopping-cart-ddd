package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Inventario extends AggregateRoot {
    private final Integer stock;
    private final UUID idProducto;

    public Inventario(UUID id, Integer stock, UUID idProducto) {
        super(id);
        this.stock = stock;
        this.idProducto = idProducto;
    }
}
