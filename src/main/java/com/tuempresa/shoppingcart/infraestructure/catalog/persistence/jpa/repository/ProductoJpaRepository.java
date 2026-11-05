package com.tuempresa.shoppingcart.infraestructure.catalog.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.catalog.persistence.jpa.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoJpaRepository extends JpaRepository <ProductoEntity, UUID>{

    boolean existsByCodigoSku(String codigoSku);

    Page<ProductoEntity> findAll(Pageable pageable);
}
