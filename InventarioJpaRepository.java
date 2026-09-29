package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventarioJpaRepository extends JpaRepository<InventarioEntity, UUID> {
}
