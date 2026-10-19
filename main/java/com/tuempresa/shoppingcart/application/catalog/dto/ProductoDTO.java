package com.tuempresa.shoppingcart.application.catalog.dto;

import com.tuempresa.shoppingcart.shared.enums.EstadoProducto;
import com.tuempresa.shoppingcart.shared.enums.Moneda;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDTO {

    private UUID id;

    @NotBlank(message = "El nombre del producto debe ser válido.")
    private String nombre;

    @NotBlank(message = "La descripción del producto debe ser válida.")
    private String descripcion;

    @NotNull(message = "El precio del producto debe ser válido.")
    @Positive(message = "El precio del producto debe ser un valor positivo.")
    private BigDecimal precio;

    @NotNull(message = "La divisa del producto debe ser válida.")
    private Moneda moneda;

    @NotBlank(message = "El código del producto debe ser válido.")
    private String codigoSku;

    @NotNull(message = "El estado del producto debe ser válido.")
    private EstadoProducto estado;

    @NotNull(message = "La categoria del producto debe ser válida.")
    private UUID idCategoria;
}
