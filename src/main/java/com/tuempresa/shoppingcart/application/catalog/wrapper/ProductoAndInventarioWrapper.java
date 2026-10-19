package com.tuempresa.shoppingcart.application.catalog.wrapper;

import com.tuempresa.shoppingcart.application.catalog.dto.InventarioDTO;
import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoAndInventarioWrapper {
    @Valid
    @NotNull(message = "El producto a registrar debe ser válido.")
    private ProductoDTO producto;

    @Valid
    @NotNull(message = "El inventario del producto a registrar debe ser válido.")
    private InventarioDTO inventario;
}
