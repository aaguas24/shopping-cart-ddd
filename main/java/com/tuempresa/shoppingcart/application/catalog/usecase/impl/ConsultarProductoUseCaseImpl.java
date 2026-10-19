package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.ConsultarProductoUseCase;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.mapper.ProductoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConsultarProductoUseCaseImpl implements ConsultarProductoUseCase {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ConsultarProductoUseCaseImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoDTO> findAll() {
       try {
           return productoMapper.ToDTO(productoRepository.findAll());
       }catch (ArgumentoInvalidoException arg){
           throw  new ArgumentoInvalidoException(arg.getMessage());
       }catch (Exception ex){
           throw  new RuntimeException(ex.getMessage());
       }
    }
}
