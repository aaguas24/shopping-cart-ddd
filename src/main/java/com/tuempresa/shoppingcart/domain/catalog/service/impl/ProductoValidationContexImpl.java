package com.tuempresa.shoppingcart.domain.catalog.service.impl;

import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationContex;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoValidationContexImpl implements ProductoValidationContex {
    private final List<ProductoValidationStrategy> strategies;

    public ProductoValidationContexImpl(List<ProductoValidationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    @Transactional(readOnly = true)
    public void validar(Object object) throws Exception {
        for (ProductoValidationStrategy strategy : strategies){
            strategy.validar(object);
        }
    }
}
