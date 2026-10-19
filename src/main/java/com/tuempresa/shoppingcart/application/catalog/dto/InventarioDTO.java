package com.tuempresa.shoppingcart.application.catalog.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    private UUID id;

    @NotNull(message = "El stock del producto debe ser válido.")
    @Positive(message = "El stock del producto debe ser válido.")
    private Integer stock;

    private UUID idProducto;
}
