package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
public interface RegistrarProductoUseCase {
    ProductoDTO registrar(ProductoDTO producto);
    public void update(ProductoDTO producto);
}
