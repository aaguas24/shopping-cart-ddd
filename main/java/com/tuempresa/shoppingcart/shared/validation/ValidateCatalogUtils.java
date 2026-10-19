package com.tuempresa.shoppingcart.shared.validation;

import java.util.Objects;


public final class ValidateCatalogUtils {
    public static final boolean validarString(Object valor, String valor2){
        if (Objects.isNull(valor) )
            return false;
        if (Objects.isNull(valor2) || valor2.isEmpty())
            return false;
        return true;
    }

    public static final boolean validarString(String valor){

        return Objects.isNull(valor) || valor.isEmpty();
    }
}
