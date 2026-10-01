package com.tuempresa.shoppingcart.shared.mapper;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source ="categoria.id", target = "idCategoria")
    @Mapping(target = "precio", expression = "java(new Precio(producto.getPrecio(), producto.getMoneda()))")
    Producto toDomain(ProductoEntity producto);

    List<Producto>  toDomain(List<ProductoEntity> productos);

    default Optional<Producto>  toDomainOptional(Optional<ProductoEntity> producto){
        return producto.map(this::toDomain);
    }

    @Mapping(source = "precio.precio", target = "precio")
    @Mapping(source = "precio.moneda", target = "moneda")
    ProductoEntity toEntity(Producto producto);

}
