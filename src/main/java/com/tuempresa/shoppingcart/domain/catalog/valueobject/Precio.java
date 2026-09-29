package com.tuempresa.shoppingcart.domain.catalog.valueobject;

import com.tuempresa.shoppingcart.shared.enums.Moneda;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.validation.ValidateCatalogUtils;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
@Getter
public class Precio {
    private  BigDecimal precio;
    private  Moneda moneda;

    public Precio() {

    }
    public Precio(BigDecimal precio, Moneda moneda) {
        if(!isValidMoneda(moneda))
            throw new ArgumentoInvalidoException("Moneda no válida.");
        if (!isValidPrecio( precio))
            throw new ArgumentoInvalidoException("Precio no válido.");
        this.precio = precio;
        this.moneda = moneda;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Precio) {
            var precioObj = (Precio) obj;
            if(Objects.isNull(precioObj))
                return false;
            if (!isValidMoneda(precioObj.moneda) || !isValidPrecio(precioObj.precio))
                return false;

            if (!isEqualsMoneda(precioObj.moneda)) {
                return false;
            }

            return this.precio.compareTo(precioObj.precio) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio.stripTrailingZeros(), moneda);
    }

    private boolean isValidPrecio(BigDecimal precio) {
        return Objects.nonNull(precio) && precio.compareTo(BigDecimal.ZERO) > 0;
    }

    public Precio sumar(Precio precio){
        validarOperacionesAritmeticas(precio);
        return new Precio(this.precio.add(precio.precio), this.moneda);
    }

    public Precio restar(Precio precio){
        validarOperacionesAritmeticas(precio);
        return new Precio(this.precio.subtract(precio.precio), this.moneda);
    }
    public Precio multiplicar(Precio precio){
        validarOperacionesAritmeticas(precio);
        return new Precio(this.precio.multiply(precio.precio), this.moneda);
    }
    public Precio dividir(Precio precio){
        validarOperacionesAritmeticas(precio);
        if (precio.precio.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArgumentoInvalidoException("No se puede dividir por cero.");
        }
        return new Precio(this.precio.divide(precio.precio, 2, RoundingMode.HALF_UP), this.moneda);
    }

    private void validarOperacionesAritmeticas(Precio precio) {
        if(Objects.isNull(precio))
            throw new ArgumentoInvalidoException("No se puede realizar esta operación.");
        if (!isValidMoneda(precio.moneda) || !isValidPrecio(precio.precio))
            throw new ArgumentoInvalidoException("No se puede realizar esta operación.");
        if (!isEqualsMoneda(precio.moneda)) {
             throw new ArgumentoInvalidoException("No se puede operar con monedas diferentes.");
        }
    }

    public boolean isEqualsMoneda(Moneda moneda) {
        if ( ValidateCatalogUtils.validarString(moneda, moneda.getDescripcion())) {
            return moneda.getDescripcion().equals(this.moneda.getDescripcion());
        }
        return false;
    }

    public boolean isValidMoneda(Moneda moneda) {
        return ValidateCatalogUtils.validarString(moneda, moneda.getDescripcion());
    }
}
