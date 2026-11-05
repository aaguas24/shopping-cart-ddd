package com.tuempresa.shoppingcart.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoCliente {
    ACTIVO("1","ACTIVO"),
    INACTIVO("2","INACTIVO"),
    AGOTADO("3","BLOQUEADO");

    private String id;
    private String descripripcion;
}
