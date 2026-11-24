package com.tuempresa.shoppingcart.domain.catalog.service.impl;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationContext;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;

import java.util.List;

public class ProductoValidationContextImpl implements ProductoValidationContext {
    private final List<ProductoValidationStrategy> strategies;

    public ProductoValidationContextImpl(List<ProductoValidationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public void validar(Producto object) {
        for (ProductoValidationStrategy strategy : strategies){
            strategy.validar(object);
        }
    }
}
