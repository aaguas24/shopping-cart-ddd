package com.tuempresa.shoppingcart.infraestructure.catalog.persistence.jpa.repository;

import com.tuempresa.shoppingcart.infraestructure.catalog.persistence.jpa.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, UUID> {
}
