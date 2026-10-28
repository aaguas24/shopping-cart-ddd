package com.tuempresa.shoppingcart.domain.catalog.service;

import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

public interface CategoriaValidationStrategy {
    void validar(Object object) throws JpaExcepcion;
}
