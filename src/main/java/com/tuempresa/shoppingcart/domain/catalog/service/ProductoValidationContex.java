package com.tuempresa.shoppingcart.domain.catalog.service;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;

public interface ProductoValidationContex {
    public void validar(Producto object) throws Exception;
}
