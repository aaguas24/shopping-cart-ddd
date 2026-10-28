package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.dto.PaginacionDTO;
import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ConsultarProductoUseCase {
    Page<ProductoDTO> findAll(PaginacionDTO paginacion);
}
