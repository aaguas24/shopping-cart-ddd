package com.tuempresa.shoppingcart.infraestructure.persistence.adapter;

import com.tuempresa.shoppingcart.domain.catalog.entity.Inventario;
import com.tuempresa.shoppingcart.domain.catalog.repository.InventarioRepository;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository.InventarioJpaRepository;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import com.tuempresa.shoppingcart.shared.mapper.InventarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventarioAdapter implements InventarioRepository {
    private static final Logger logger = LoggerFactory.getLogger(InventarioAdapter.class);
    private final InventarioJpaRepository inventarioJpaRepository;
    private final InventarioMapper inventarioMapper;

    public InventarioAdapter(InventarioJpaRepository inventarioJpaRepository, InventarioMapper inventarioMapper){
        this.inventarioJpaRepository = inventarioJpaRepository;
        this.inventarioMapper = inventarioMapper;
    }

    @Override
    public Inventario save(Inventario inventario) throws JpaExcepcion {
        try {
            var inventarioEntity = inventarioMapper.toEntity(inventario);
            var inventarioEntityResponse= inventarioJpaRepository.save(inventarioEntity);
            return inventarioMapper.toDomain(inventarioEntityResponse);
        } catch (Exception e) {
            logger.error("InventarioAdapter - save: Error al guardar el inventario: {}", e.getMessage());
            throw new JpaExcepcion(e.getMessage());
        }
    }

    @Override
    public Inventario findByIdProducto(UUID idProducto) throws JpaExcepcion {
        try {
            var inventario = inventarioJpaRepository.findByProductoId(idProducto);
            if (!inventario.isPresent())
                return null;

            return inventarioMapper.toDomain(inventario.get());
        } catch (Exception e) {
            logger.error("InventarioAdapter - findByIdProducto: Error al guardar el inventario: {}", e.getMessage());
            throw new JpaExcepcion(e.getMessage());
        }
    }
}
