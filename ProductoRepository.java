package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository {

    public List<Producto> findAll() throws JpaExcepcion;
    public Optional<Producto> findById(UUID id) throws JpaExcepcion;
    public Optional<Producto> save(Producto producto) throws JpaExcepcion;
}
