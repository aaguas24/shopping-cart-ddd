package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Inventario;

import java.util.UUID;

public interface InventarioRepository {
    Inventario save(Inventario inventario);
    Inventario findByIdProducto(UUID idProducto);
}
