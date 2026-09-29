package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoJpaRepository extends JpaRepository <ProductoEntity, UUID>{

}
