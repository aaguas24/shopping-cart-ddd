package com.tuempresa.shoppingcart.shared.mapper;

import com.tuempresa.shoppingcart.application.catalog.dto.InventarioDTO;
import com.tuempresa.shoppingcart.domain.catalog.entity.Inventario;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.InventarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarioMapper {
    @Mapping(source ="producto.id",target = "idProducto")
    Inventario toDomain(InventarioEntity inventario);

    @Mapping(source ="idProducto",target = "producto.id")
    InventarioEntity toEntity(Inventario inventario);

    Inventario toDomain(InventarioDTO inventario);

    InventarioDTO toDto(Inventario inventario);
}
