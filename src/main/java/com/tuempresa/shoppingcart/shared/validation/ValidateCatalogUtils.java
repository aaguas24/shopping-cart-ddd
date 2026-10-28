package com.tuempresa.shoppingcart.shared.validation;

import java.util.Objects;


public final class ValidateCatalogUtils {
    private ValidateCatalogUtils() {
    }

    public static final boolean validarString(Object valor, String valor2){
        return !Objects.isNull(valor) && !Objects.isNull(valor2) && !valor2.isEmpty();
    }

    public static final boolean validarString(String valor){

        return Objects.isNull(valor) || valor.isEmpty();
    }
}
