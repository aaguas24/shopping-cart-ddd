package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Inventario extends AggregateRoot {
    private Integer stock;
    private final UUID idProducto;

    public Inventario(UUID id, Integer stock, UUID idProducto) {
        super(id);
        validarStock(stock);
        validarProducto(idProducto);
        this.stock = stock;
        this.idProducto = idProducto;
    }

    public void adicionarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new ArgumentoInvalidoException("La cantidad a adicionar debe ser positiva.");
        }
        this.stock += cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad <= 0) {
            throw new ArgumentoInvalidoException("La cantidad a reducir debe ser positiva.");
        }
        if (this.stock < cantidad) {
            throw new ArgumentoInvalidoException("Stock insuficiente para realizar la operación.");
        }
        this.stock -= cantidad;
    }

    private void validarStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new ArgumentoInvalidoException("El stock inicial no puede ser negativo.");
        }
    }

    private void validarProducto(UUID idProducto) {
        if (idProducto == null) {
            throw new ArgumentoInvalidoException("El ID del producto es obligatorio.");
        }
    }
}
