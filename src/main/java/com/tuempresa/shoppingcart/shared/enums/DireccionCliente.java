package com.tuempresa.shoppingcart.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DireccionCliente {
    ACTIVO("1","ENVIO"),
    INACTIVO("2","FACTURACION");

    private String id;
    private String descripripcion;
}
