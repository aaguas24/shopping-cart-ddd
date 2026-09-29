package com.tuempresa.shoppingcart.shared.exception;

public class ArgumentoInvalidoException extends RuntimeException {
    public ArgumentoInvalidoException(String mensaje){
        super(mensaje);
    }
}
