package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Categoria;

import java.util.List;
import java.util.UUID;

public interface CategoriaRepository {

    public Categoria save(Categoria categoria);
    public List<Categoria> findAll();
    public boolean existById(UUID id);
}
