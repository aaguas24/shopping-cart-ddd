package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import com.tuempresa.shoppingcart.domain.catalog.valueobject.Precio;
import com.tuempresa.shoppingcart.shared.enums.EstadoProducto;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.validation.ValidateCatalogUtils;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Producto extends AggregateRoot {
    private final String nombre;
    private final String descripcion;
    private final Precio precio;
    private final String codigoSku;
    private final EstadoProducto estado;
    private final UUID idCategoria;

    public Producto(UUID id, String nombre, String descripcion, Precio precio, String codigoSku, UUID idCategoria, EstadoProducto estado) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigoSku = codigoSku;
        this.idCategoria = idCategoria;
        this.estado = estado;
        validarAtributos(this);
    }



    public void validarAtributos(Producto producto){
        if(Objects.isNull(producto))
            throw new ArgumentoInvalidoException("El producto debe ser válido.");
        if(ValidateCatalogUtils.validarString(producto.nombre))
            throw new ArgumentoInvalidoException("El nombre debe ser válido.");
        if(ValidateCatalogUtils.validarString(producto.descripcion))
            throw new ArgumentoInvalidoException("La descripción debe ser válida.");
        if(ValidateCatalogUtils.validarString(producto.codigoSku))
            throw new ArgumentoInvalidoException("La referencia comercial debe ser válida.");
        if(Objects.isNull(producto.estado))
            throw new ArgumentoInvalidoException("El estado debe ser válido.");

    }

}
