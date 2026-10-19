package com.tuempresa.shoppingcart.domain.catalog.service;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

public interface ProductoValidationStrategy {
    void validar(Producto object) throws JpaExcepcion;
}
