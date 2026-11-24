package com.tuempresa.shoppingcart.infraestructure.config;

import com.tuempresa.shoppingcart.domain.catalog.repository.CategoriaRepository;
import com.tuempresa.shoppingcart.domain.catalog.repository.ProductoRepository;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationContext;
import com.tuempresa.shoppingcart.domain.catalog.service.ProductoValidationStrategy;
import com.tuempresa.shoppingcart.domain.catalog.service.impl.ProductoValidationContextImpl;
import com.tuempresa.shoppingcart.domain.catalog.service.strategy.ValidarExistenciaCategoriaStrategy;
import com.tuempresa.shoppingcart.domain.catalog.service.strategy.ValidarExistenciaProductoStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DomainConfig {

    @Bean
    public ValidarExistenciaCategoriaStrategy validarExistenciaCategoriaStrategy(CategoriaRepository categoriaRepository) {
        return new ValidarExistenciaCategoriaStrategy(categoriaRepository);
    }

    @Bean
    public ValidarExistenciaProductoStrategy validarExistenciaProductoStrategy(ProductoRepository productoRepository) {
        return new ValidarExistenciaProductoStrategy(productoRepository);
    }

    @Bean
    public ProductoValidationContext productoValidationContext(List<ProductoValidationStrategy> strategies) {
        return new ProductoValidationContextImpl(strategies);
    }
}
