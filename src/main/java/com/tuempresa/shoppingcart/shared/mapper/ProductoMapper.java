package com.tuempresa.shoppingcart.shared.mapper;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.valueobject.Precio;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.ProductoEntity;
import com.tuempresa.shoppingcart.shared.enums.Moneda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "categoria.id", target = "idCategoria")
    @Mapping(target = "precio", expression = "java(mapBigDecimalToPrecio(producto.getPrecio(), producto.getMoneda()))")
    Producto toDomain(ProductoEntity producto);

    default Page<Producto> toDomainPage(Page<ProductoEntity> producto) {
        if (producto == null) {
            return Page.empty();
        }
        return producto.map(this::toDomain);
    }

    default List<Producto> toDomain(List<ProductoEntity> productos) {
        if (productos == null) {
            return List.of();
        }
        return productos.stream().map(this::toDomain).toList();
    }

    default Page<ProductoDTO> toDTO(Page<Producto> productos) {
        if (productos == null) {
            return Page.empty();
        }
        return productos.map(this::toDTO);
    }

    default Optional<Producto> toDomainOptional(Optional<ProductoEntity> producto){
        return producto.map(this::toDomain);
    }


    @Mapping(source = "precio", target = "precio", qualifiedByName = "extraerPrecioValue")
    @Mapping(source = "precio", target = "moneda", qualifiedByName = "extraerMoneda")
    @Mapping(source = "idCategoria", target = "categoria.id")
    ProductoEntity toEntity(Producto producto);

    @Mapping(target = "precio", expression = "java(mapBigDecimalToPrecio(producto.getPrecio(), producto.getMoneda()))")
    Producto toDomain(ProductoDTO producto);

    @Mapping(source = "precio", target = "precio", qualifiedByName = "extraerPrecioValue")
    @Mapping(source = "precio", target = "moneda", qualifiedByName = "extraerMoneda")
    ProductoDTO toDTO(Producto producto);

    default Precio mapBigDecimalToPrecio(BigDecimal precioValue, Moneda moneda) {
        if (precioValue == null || moneda == null) {
            return null;
        }
        return Precio.of(precioValue, moneda);
    }

    @Named("extraerMoneda")
    default Moneda extraerMoneda(Precio precio) {
        if (Objects.isNull(precio) || Objects.isNull(precio.getMoneda())) {
            return null;
        }
        return precio.getMoneda();
    }

    @Named("extraerPrecioValue")
    default BigDecimal extraerPrecioValue(Precio precio) {
        if (Objects.isNull(precio) || Objects.isNull(precio.getPrecio())) {
            return null;
        }
        return precio.getPrecio();
    }
}
