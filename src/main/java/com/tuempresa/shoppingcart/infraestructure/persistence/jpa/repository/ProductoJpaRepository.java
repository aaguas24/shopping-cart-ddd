package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoJpaRepository extends JpaRepository <ProductoEntity, UUID>{

    @Query(value = "SELECT EXIST(SELECT 1 FROM producto WHERE  id = :id OR codigo_sku= :codigoSku)", nativeQuery = true)
    boolean existIdOrCodigoSku(@Param("id") UUID id, @Param("codigoSku") String codigoSku);
}
