package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Categoria;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

import java.util.List;
import java.util.UUID;

public interface CategoriaRepository {

    public Categoria save(Categoria categoria) throws JpaExcepcion;
    public List<Categoria> findAll() throws JpaExcepcion;
    public boolean existById(UUID id)throws JpaExcepcion;
}
