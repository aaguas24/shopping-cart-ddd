package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoUseCase;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import com.tuempresa.shoppingcart.domain.catalog.service.strategy.ValidarExistenciaCategoriaStrategy;
import com.tuempresa.shoppingcart.shared.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProductoUseCaseImpl implements RegistrarProductoUseCase {
    private final ProductoRepository productoRepo;

    @Qualifier("validarExistenciaProductoStrategy")
    private final ProductoValidationStrategy validarExistenciaProducto;

    @Qualifier("validarExistenciaCategoriaStrategy")
    private final ValidarExistenciaCategoriaStrategy validarExistenciaCategoriaStrategy;

    private final ProductoMapper productoMapper;

    public RegistrarProductoUseCaseImpl(ProductoRepository productoRepo, 
                                      ProductoValidationStrategy validarExistenciaProducto, 
                                      ValidarExistenciaCategoriaStrategy validarExistenciaCategoriaStrategy, 
                                      ProductoMapper productoMapper) {
        this.productoRepo = productoRepo;
        this.validarExistenciaProducto = validarExistenciaProducto;
        this.validarExistenciaCategoriaStrategy = validarExistenciaCategoriaStrategy;
        this.productoMapper = productoMapper;
    }

    @Override
    public ProductoDTO registrar(ProductoDTO producto) {
        validarExistenciaCategoriaStrategy.validar(producto.getIdCategoria());
        var productoDomain = productoMapper.toDomain(producto);
        validarExistenciaProducto.validar(productoDomain);
        var productoResponse = productoRepo.save(productoDomain);
        return productoResponse.map(productoMapper::toDTO).orElseGet(ProductoDTO::new);
    }

    public void update(ProductoDTO producto) {
        var productoDomain = productoMapper.toDomain(producto);
        productoRepo.updateImages(productoDomain);
    }
}
