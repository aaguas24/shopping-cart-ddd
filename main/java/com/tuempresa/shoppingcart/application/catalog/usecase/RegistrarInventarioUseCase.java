package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.dto.InventarioDTO;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;

public interface RegistrarInventarioUseCase {
    InventarioDTO registrar(InventarioDTO inventarioDTO) throws JpaExcepcion;
}
