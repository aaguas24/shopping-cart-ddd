package com.tuempresa.shoppingcart.domain.catalog.service.strategy;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

import org.springframework.stereotype.Service;

@Service("validarExistenciaProductoStrategy")
public class ValidarExistenciaProductoStrategy implements ProductoValidationStrategy {
    private final ProductoRepository productoRepository;

    public ValidarExistenciaProductoStrategy(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public void validar(Producto producto) throws JpaExcepcion {
        try {
            if (productoRepository.existById(producto.getId()).equals(Boolean.TRUE))
                throw new ArgumentoInvalidoException("El producto ya se encuentra registrado.");
        } catch (ArgumentoInvalidoException ex) {
            throw new ArgumentoInvalidoException(ex.getMessage());
        } catch (JpaExcepcion ex) {
            throw new JpaExcepcion(ex.getMessage());
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage());
        }

    }

}
