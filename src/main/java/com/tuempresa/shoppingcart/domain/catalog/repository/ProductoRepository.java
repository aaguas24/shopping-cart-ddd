package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository {

    public Page<Producto> findAll(Pageable pageable) throws JpaExcepcion;
    public Optional<Producto> findById(UUID id) throws JpaExcepcion;
    public Optional<Producto> save(Producto producto) throws JpaExcepcion;
    public Boolean existById(UUID id) throws JpaExcepcion;
    public Boolean existByIdOrCodigoSku(String codigoSku) throws JpaExcepcion;
}
