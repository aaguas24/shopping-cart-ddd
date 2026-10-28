package com.tuempresa.shoppingcart.domain.catalog.service.strategy;

import com.tuempresa.shoppingcart.domain.catalog.repository.CategoriaRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.CategoriaValidationStrategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("validarExistenciaCategoriaStrategy")
public class ValidarExistenciaCategoriaStategy implements CategoriaValidationStrategy {

    private final CategoriaRepository categoriaRepository;

    public ValidarExistenciaCategoriaStategy(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(Object categoria) throws JpaExcepcion {
        try {
            UUID id = (UUID) categoria;
            if (!categoriaRepository.existById(id))
                throw new ArgumentoInvalidoException("No existe la categoria con id: "+id.toString());

        }catch (ArgumentoInvalidoException arg){
            throw new ArgumentoInvalidoException(arg.getMessage());
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }
}
