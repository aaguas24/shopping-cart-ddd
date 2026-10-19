package com.tuempresa.shoppingcart.shared.mapper;

import com.tuempresa.shoppingcart.application.catalog.dto.CategoriaDTO;
import com.tuempresa.shoppingcart.domain.catalog.entity.Categoria;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.CategoriaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaEntity toEntity(Categoria categoria);

    Categoria toDomain(CategoriaEntity categoria);

    List<Categoria> toListDomain(List<CategoriaEntity> categoria);

    Categoria toDomain(CategoriaDTO categoria);

    CategoriaDTO toDTO(Categoria categoria);
}
