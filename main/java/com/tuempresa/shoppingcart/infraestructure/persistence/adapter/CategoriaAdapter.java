package com.tuempresa.shoppingcart.infraestructure.persistence.adapter;

import com.tuempresa.shoppingcart.domain.catalog.entity.Categoria;
import com.tuempresa.shoppingcart.domain.catalog.repository.CategoriaRepository;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.repository.CategoriaJpaRepository;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import com.tuempresa.shoppingcart.shared.mapper.CategoriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaAdapter implements CategoriaRepository {
    private static final Logger logger = LoggerFactory.getLogger(CategoriaAdapter.class);

    private final CategoriaJpaRepository categoriaJpaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaAdapter(CategoriaJpaRepository categoriaJpaRepository, CategoriaMapper categoriaMapper){
        this.categoriaJpaRepository = categoriaJpaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public Categoria save(Categoria categoria) throws JpaExcepcion {
        try {
            var categoriaEntity = categoriaMapper.toEntity(categoria);
            var categoriaResponse = categoriaJpaRepository.save(categoriaEntity);
            return categoriaMapper.toDomain(categoriaResponse);
        }catch (Exception ex){
            logger.error("CategoriaAdapter -  save: Error al guardar la categoria: {}", ex.getMessage());
            throw new JpaExcepcion(ex.getMessage());
        }
    }

    @Override
    public List<Categoria> findAll() throws JpaExcepcion {
        try {
           var categoriaResponse = categoriaJpaRepository.findAll();
           return categoriaMapper.toListDomain(categoriaResponse);
        } catch (Exception ex) {
            logger.error("CategoriaAdapter -  findAll: Error al consultar todas las categoria: {}", ex.getMessage());
            throw new JpaExcepcion(ex.getMessage());
        }
    }

    @Override
    public boolean existById(UUID id) throws JpaExcepcion {
        try {
            return categoriaJpaRepository.existsById(id);
        }catch (Exception ex){
            throw new JpaExcepcion(ex.getMessage());
        }
    }
}
