package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.tuempresa.shoppingcart.application.catalog.dto.CategoriaDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarCategoriaUseCase;
import com.tuempresa.shoppingcart.domain.catalog.repository.CategoriaRepository;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import com.tuempresa.shoppingcart.shared.mapper.CategoriaMapper;
import org.springframework.stereotype.Service;

@Service
public class RegistrarCategoriaUseCaseImpl implements RegistrarCategoriaUseCase {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public  RegistrarCategoriaUseCaseImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper){
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaDTO registrar(CategoriaDTO categoria) {
        var categoriaDomain = categoriaMapper.toDomain(categoria);
        var categoriaSave = categoriaRepository.save(categoriaDomain);
        return categoriaMapper.toDTO(categoriaSave);
    }
}
