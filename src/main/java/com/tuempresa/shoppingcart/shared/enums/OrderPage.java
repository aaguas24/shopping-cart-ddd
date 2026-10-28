package com.tuempresa.shoppingcart.shared.enums;

import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

public enum OrderPage {
    DESCENDENTE(1, "descendente", Sort.Direction.DESC),
    ASCENDENTE(2, "ascendente", Sort.Direction.ASC);

    private int id;
    private String descripcion;
    private Sort.Direction orden;

    OrderPage(int id, String descripcion, Sort.Direction orden) {
        this.id = id;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Sort.Direction getOrden() {
        return orden;
    }

    private static OrderPage findByDescripcion(String descripcion) {

        return Arrays.stream(OrderPage.values()).filter(value ->
                descripcion.equals(value.descripcion)).findFirst().orElseThrow(() -> new ArgumentoInvalidoException("Orden no válido."));
    }
}
