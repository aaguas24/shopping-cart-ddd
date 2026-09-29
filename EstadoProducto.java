package com.tuempresa.shoppingcart.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoProducto {
    ACTIVO("1","ACTIVO"),
    INACTIVO("2","INACTIVO"),
    AGOTADO("3","AGOTADO");

    private String id;
    private String descripripcion;
}
