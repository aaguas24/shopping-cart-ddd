package com.tuempresa.shoppingcart.shared.exception;

public class JpaExcepcion extends RuntimeException {

    public JpaExcepcion(String mensaje){
       super(mensaje);
    }
}
