package com.tuempresa.shoppingcart.infraestructure.catalog.persistence.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoUseCase;
import com.tuempresa.shoppingcart.infraestructure.config.KafkaProperties;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductoListener {

    private final ObjectMapper objectMapper;
    private final RegistrarProductoUseCase registrarProductoUseCase;
    private final KafkaProperties kafkaProperties;

    public ProductoListener(ObjectMapper objectMapper, RegistrarProductoUseCase registrarProductoUseCase, KafkaProperties kafkaProperties) {
        this.objectMapper = objectMapper;
        this.registrarProductoUseCase = registrarProductoUseCase;
        this.kafkaProperties = kafkaProperties;
    }

    @KafkaListener(topics = "#{kafkaProperties.productoImagenActualizado}")
    public void updateProducto( String data) throws JsonProcessingException, JpaExcepcion {
        var producto = objectMapper.readValue(data, ProductoDTO.class);
        registrarProductoUseCase.update(producto);
    }
}
