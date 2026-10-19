package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

public interface RegistrarProductoUseCase {
    ProductoDTO registrar(ProductoDTO producto) throws JpaExcepcion;
}
