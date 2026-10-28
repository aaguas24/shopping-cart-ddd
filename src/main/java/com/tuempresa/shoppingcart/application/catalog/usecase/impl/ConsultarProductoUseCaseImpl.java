package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.tuempresa.shoppingcart.application.catalog.dto.PaginacionDTO;
import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.ConsultarProductoUseCase;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.mapper.ProductoMapper;
import com.tuempresa.shoppingcart.shared.validation.ValidatePageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConsultarProductoUseCaseImpl implements ConsultarProductoUseCase {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ConsultarProductoUseCaseImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public Page<ProductoDTO> findAll(PaginacionDTO paginacion) {
        try {
            var pageable = ValidatePageableUtils.validate(paginacion);
            return productoMapper.toDTO(productoRepository.findAll(pageable));
        } catch (ArgumentoInvalidoException arg) {
            throw new ArgumentoInvalidoException(arg.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
