package com.tuempresa.shoppingcart.domain.catalog.service;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;

public interface ProductoValidationStrategy {
    void validar(Producto object);
}
