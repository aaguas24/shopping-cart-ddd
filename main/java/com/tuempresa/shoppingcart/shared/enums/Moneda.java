package com.tuempresa.shoppingcart.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Moneda {
    PESO("1","COP"),
    DOLAR("2", "USD"),
    EURO("3", "EUR");

    private String id;
    private String descripcion;
}
