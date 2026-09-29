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
    private String nombre;
    private String descripcion;
    private Precio precio;
    private String sku;
    private EstadoProducto estado;
    private UUID idCategoria;

    public Producto(UUID id, String nombre, String descripcion, Precio precio, String sku, UUID idCategoria, EstadoProducto estado) {
        super(id);
        validarAtributos(this);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.sku = sku;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }

    public void validarAtributos(Producto producto){
        if(Objects.isNull(producto))
            throw new ArgumentoInvalidoException("El producto debe ser válido.");
        if(ValidateCatalogUtils.validarString(producto.nombre))
            throw new ArgumentoInvalidoException("El nombre debe ser válido.");
        if(ValidateCatalogUtils.validarString(producto.descripcion))
            throw new ArgumentoInvalidoException("La descripción debe ser válida.");
        if(ValidateCatalogUtils.validarString(producto.sku))
            throw new ArgumentoInvalidoException("La referencia comercial debe ser válida.");
        if(Objects.isNull(producto.estado))
            throw new ArgumentoInvalidoException("El estado debe ser válido.");

    }

}
