package com.tuempresa.shoppingcart.domain.catalog.service.strategy;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ValidarExistenciaProductoStrategy")
public class ValidarExistenciaProductoStrategy implements ProductoValidationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ValidarExistenciaProductoStrategy.class);
    private final ProductoRepository productoRepository;

    public ValidarExistenciaProductoStrategy(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }
    @Override
    public void validar(Object object) throws Exception {
        try {
            Producto producto = (Producto) object;
        if(productoRepository.existById(producto.getId()))
            throw new ArgumentoInvalidoException("El producto ya se encuentra registrado.");

        }catch (ArgumentoInvalidoException ex){
            logger.error("ValidarExistenciaProductoStrategy -- exist(): ", ex.getMessage());
            throw new ArgumentoInvalidoException(ex.getMessage());
        }
        catch (Exception ex){
            logger.error("ValidarExistenciaProductoStrategy -- exist(): ", ex.getMessage());
            throw new JpaExcepcion(ex.getMessage());
        }

    }
}
