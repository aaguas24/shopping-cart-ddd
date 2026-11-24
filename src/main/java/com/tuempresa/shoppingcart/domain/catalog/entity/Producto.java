package com.tuempresa.shoppingcart.domain.catalog.entity;

import com.tuempresa.shoppingcart.domain.catalog.aggregated.AggregateRoot;
import com.tuempresa.shoppingcart.domain.catalog.valueobject.Precio;
import com.tuempresa.shoppingcart.shared.enums.EstadoProducto;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Producto extends AggregateRoot {
    private String nombre;
    private String descripcion;
    private final List<String> imagenUrl;
    private Precio precio;
    private final String codigoSku;
    private EstadoProducto estado;
    private final UUID idCategoria;
    private final LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    // Constantes de negocio
    private static final int MAX_NOMBRE_LENGTH = 100;
    private static final int MIN_DESCRIPCION_LENGTH = 10;
    private static final String SKU_REGEX = "^[A-Z0-9-]{5,20}$";

    public Producto(UUID id, String nombre, String descripcion, List<String> imagenUrl, Precio precio, String codigoSku,
                    EstadoProducto estado, UUID idCategoria, LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {
        super(id);
        validarNombre(nombre);
        validarDescripcion(descripcion);
        validarSku(codigoSku);
        validarPrecio(precio);
        validarCategoria(idCategoria);
        
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl == null ? List.of() : List.copyOf(imagenUrl);
        this.precio = precio;
        this.codigoSku = codigoSku.toUpperCase();
        this.estado = estado == null ? EstadoProducto.ACTIVO : estado;
        this.idCategoria = idCategoria;
        this.fechaRegistro = fechaRegistro == null ? LocalDateTime.now() : fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public void cambiarNombre(String nuevoNombre) {
        validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void actualizarPrecio(Precio nuevoPrecio) {
        validarPrecio(nuevoPrecio);
        // Regla de negocio: El precio no puede variar más del 50% de un solo golpe sin revisión
        BigDecimal variacion = nuevoPrecio.getPrecio().divide(this.precio.getPrecio(), 2, RoundingMode.HALF_UP);
        if (variacion.compareTo(new BigDecimal("1.5")) > 0 || variacion.compareTo(new BigDecimal("0.5")) < 0) {
            // Podríamos lanzar una excepción específica o marcar para revisión
        }
        this.precio = nuevoPrecio;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void desactivar() {
        if (this.estado == EstadoProducto.INACTIVO) {
            throw new ArgumentoInvalidoException("El producto ya está inactivo.");
        }
        this.estado = EstadoProducto.INACTIVO;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void activar() {
        this.estado = EstadoProducto.ACTIVO;
        this.fechaActualizacion = LocalDateTime.now();
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ArgumentoInvalidoException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > MAX_NOMBRE_LENGTH) {
            throw new ArgumentoInvalidoException("El nombre no puede superar los " + MAX_NOMBRE_LENGTH + " caracteres.");
        }
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().length() < MIN_DESCRIPCION_LENGTH) {
            throw new ArgumentoInvalidoException("La descripción debe tener al menos " + MIN_DESCRIPCION_LENGTH + " caracteres.");
        }
    }

    private void validarSku(String sku) {
        if (sku == null || !sku.matches(SKU_REGEX)) {
            throw new ArgumentoInvalidoException("El código SKU no tiene un formato válido (5-20 caracteres alfanuméricos y guiones).");
        }
    }

    private void validarPrecio(Precio precio) {
        if (precio == null) {
            throw new ArgumentoInvalidoException("El precio es obligatorio.");
        }
    }

    private void validarCategoria(UUID idCategoria) {
        if (idCategoria == null) {
            throw new ArgumentoInvalidoException("La categoría es obligatoria.");
        }
    }
}
