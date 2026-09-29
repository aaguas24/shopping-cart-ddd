package com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, UUID> {
}
