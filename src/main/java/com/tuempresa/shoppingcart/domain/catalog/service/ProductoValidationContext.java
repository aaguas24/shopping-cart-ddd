package com.tuempresa.shoppingcart.domain.catalog.service;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;

public interface ProductoValidationContext {
    void validar(Producto object);
}
