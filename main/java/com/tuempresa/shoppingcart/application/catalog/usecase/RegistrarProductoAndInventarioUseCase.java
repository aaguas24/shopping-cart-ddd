package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

public interface RegistrarProductoAndInventarioUseCase {
    ProductoAndInventarioWrapper registrar(ProductoAndInventarioWrapper productoAndInventarioWrapper) throws JpaExcepcion;
}
