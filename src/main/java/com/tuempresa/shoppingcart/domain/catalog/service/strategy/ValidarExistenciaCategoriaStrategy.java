package com.tuempresa.shoppingcart.domain.catalog.service.strategy;

import com.tuempresa.shoppingcart.domain.catalog.repository.CategoriaRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.CategoriaValidationStrategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;

import java.util.UUID;

public class
ValidarExistenciaCategoriaStrategy implements CategoriaValidationStrategy {

    private final CategoriaRepository categoriaRepository;

    public ValidarExistenciaCategoriaStrategy(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(Object categoria) {
        UUID id = (UUID) categoria;
        if (!categoriaRepository.existById(id))
            throw new ArgumentoInvalidoException("No existe la categoria con id: " + id.toString());
    }
}
