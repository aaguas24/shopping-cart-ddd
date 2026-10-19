package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoUseCase;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import com.tuempresa.shoppingcart.shared.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProductoUseCaseImpl implements RegistrarProductoUseCase {
    private final ProductoRepository productoRepo;

    @Qualifier("validarExistenciaProductoStrategy")
    private final ProductoValidationStrategy validarExistenciaProducto;

    private final ProductoMapper productoMapper;

    public RegistrarProductoUseCaseImpl(ProductoRepository productoRepo, ProductoValidationStrategy validarExistenciaProducto, ProductoMapper productoMapper) {
        this.productoRepo = productoRepo;
        this.validarExistenciaProducto = validarExistenciaProducto;
        this.productoMapper = productoMapper;
    }

    @Override
    public ProductoDTO registrar(ProductoDTO producto) throws JpaExcepcion {
        try {
            var productoDomain
                    = productoMapper.toDomain(producto);
//            validarExistenciaProducto.validar(productoDomain);
            var productoResponse =  productoRepo.save(productoDomain);
            if(productoResponse.isPresent())
                return productoMapper.toDTO(productoResponse.get());
            return new ProductoDTO();
        }catch (ArgumentoInvalidoException aex){
            throw new ArgumentoInvalidoException(aex.getMessage());
        }catch (JpaExcepcion jex){
            throw new JpaExcepcion(jex.getMessage());
        }catch (Exception ex){
            throw new IllegalStateException(ex.getMessage());
        }
    }
}
