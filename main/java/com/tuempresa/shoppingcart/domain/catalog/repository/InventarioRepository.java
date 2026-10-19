package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Inventario;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

import java.util.UUID;

public interface InventarioRepository {
    Inventario save(Inventario inventario) throws JpaExcepcion;
    Inventario findByIdProducto(UUID idProducto) throws JpaExcepcion;
}
