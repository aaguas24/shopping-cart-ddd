package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;

import java.util.List;

public interface ConsultarProductoUseCase {
    List<ProductoDTO> findAll();
}
