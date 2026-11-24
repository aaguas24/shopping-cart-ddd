package com.tuempresa.shoppingcart.domain.catalog.service.strategy;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;

public class ValidarExistenciaProductoStrategy implements ProductoValidationStrategy {
    private final ProductoRepository productoRepository;

    public ValidarExistenciaProductoStrategy(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public void validar(Producto producto) {
        if (productoRepository.existByIdOrCodigoSku(producto.getCodigoSku())) {
            throw new ArgumentoInvalidoException("El producto ya se encuentra registrado.");
        }
    }
}
