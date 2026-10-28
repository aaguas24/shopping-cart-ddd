package com.tuempresa.shoppingcart.infraestructure.persistence.adapter;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.ProductoEntity;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository.ProductoJpaRepository;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import com.tuempresa.shoppingcart.shared.mapper.ProductoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoAdapter implements ProductoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductoAdapter.class);
    private final ProductoJpaRepository productoRepo;

    private final ProductoMapper productoMapper;

    public ProductoAdapter(ProductoJpaRepository productoRepo, ProductoMapper productoMapper) {
        this.productoRepo = productoRepo;
        this.productoMapper = productoMapper;
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) throws JpaExcepcion{
        try {
            Page<ProductoEntity> productoResponse = productoRepo.findAll(pageable);

            return productoMapper.toDomainPage(productoResponse);
        } catch (Exception e) {
            logger.error("ProductoAdapter -- findAll(): ",e.getMessage());
            throw new JpaExcepcion("Error al consultar datos");
        }

    }

    @Override
    public Optional<Producto> findById(UUID id) throws JpaExcepcion{
        try {
            var productoResponse = productoRepo.findById(id);
            if (productoResponse.isEmpty())
                return Optional.empty();
            return productoMapper.toDomainOptional(productoResponse);
        }catch (Exception e) {
            logger.error("ProductoAdapter -- findById(UUID id): ",e.getMessage());
            throw new JpaExcepcion("Error al consultar datos");
        }
    }

    @Override
    public Optional<Producto> save(Producto producto) throws JpaExcepcion{
        try {
            var productoMapperResponse= productoMapper.toEntity(producto);
            ProductoEntity productoResponse = productoRepo.save(productoMapperResponse);
            return productoMapper.toDomainOptional(Optional.of(productoResponse)) ;
        } catch (Exception e) {
            logger.error("ProductoAdapter -- save(Producto producto): ",e.getMessage());
            throw new JpaExcepcion("Error al guardar producto.");
        }

    }

    @Override
    public Boolean existById(UUID id) throws JpaExcepcion {
        try {
            return productoRepo.existsById(id);
        }catch (Exception e) {
            logger.error("ProductoAdapter -- exist(UUID id): ",e.getMessage());
            throw new JpaExcepcion("Error al consultar datos");
        }
    }

    @Override
    public Boolean existByIdOrCodigoSku(String codigoSku) throws JpaExcepcion {
        try {
            return productoRepo.existsByCodigoSku(codigoSku);
        }catch (Exception e) {
            logger.error("ProductoAdapter -- existByIdOrCodigoSku(UUID id): ",e.getMessage());
            throw new JpaExcepcion("Error al consultar datos");
        }
    }
}
