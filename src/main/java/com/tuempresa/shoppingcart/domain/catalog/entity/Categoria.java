package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Categoria extends AggregateRoot {
    private String nombre;
    private String descripcion;

    private static final int MAX_NOMBRE_LENGTH = 50;

    public Categoria(UUID id, String nombre, String descripcion) {
        super(id);
        validarNombre(nombre);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void actualizar(String nombre, String descripcion) {
        validarNombre(nombre);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ArgumentoInvalidoException("El nombre de la categoría no puede estar vacío.");
        }
        if (nombre.length() > MAX_NOMBRE_LENGTH) {
            throw new ArgumentoInvalidoException("El nombre de la categoría no puede superar los " + MAX_NOMBRE_LENGTH + " caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id='" + getId() + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
