package com.tuempresa.shoppingcart.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolUsuarioEnum {
    ADMINISTRADOR("1","ADMINISTRADOR"),
    VENDEDOR("2","VENDEDOR"),
    CLIENTE("1","CLIENTE"),;

    private String id;
    private String descripripcion;
}
