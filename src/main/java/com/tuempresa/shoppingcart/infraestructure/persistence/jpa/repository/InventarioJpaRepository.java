package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InventarioJpaRepository extends JpaRepository<InventarioEntity, UUID> {
}
