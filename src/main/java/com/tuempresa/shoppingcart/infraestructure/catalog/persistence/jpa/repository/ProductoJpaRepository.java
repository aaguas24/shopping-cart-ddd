package com.tuempresa.shoppingcart.infraestructure.catalog.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.catalog.persistence.jpa.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoJpaRepository extends JpaRepository <ProductoEntity, UUID>{

    boolean existsByCodigoSku(String codigoSku);

    Page<ProductoEntity> findAll(Pageable pageable);

    @Transactional
    default void updateImagenUrlById(List<String> imagenUrl, UUID id) {
        ProductoEntity producto = findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id " + id));
        producto.setImagenUrl(imagenUrl == null ? new ArrayList<>() : new ArrayList<>(imagenUrl));
        save(producto);
    }
}
