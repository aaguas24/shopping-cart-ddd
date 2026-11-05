package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarInventarioUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoAndInventarioUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoUseCase;
import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrarProductoAndInventarioUseCaseImpl implements RegistrarProductoAndInventarioUseCase {

    private final RegistrarProductoUseCase registrarProductoUseCase;
    private final RegistrarInventarioUseCase registrarInventarioUseCase;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public RegistrarProductoAndInventarioUseCaseImpl(RegistrarProductoUseCase registrarProductoUseCase,
                                                     RegistrarInventarioUseCase registrarInventarioUseCase, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.registrarProductoUseCase = registrarProductoUseCase;
        this.registrarInventarioUseCase = registrarInventarioUseCase;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor =  JpaExcepcion.class)
    public ProductoAndInventarioWrapper registrar(ProductoAndInventarioWrapper productoAndInventarioWrapper) throws JpaExcepcion {
        try {
            ProductoAndInventarioWrapper wrapper = new ProductoAndInventarioWrapper();
            var productosResponse = registrarProductoUseCase.registrar(productoAndInventarioWrapper.getProducto());
            wrapper.setProducto(productosResponse);
            productoAndInventarioWrapper.getInventario().setIdProducto(productosResponse.getId());
            var inventarioResponse= registrarInventarioUseCase.registrar(productoAndInventarioWrapper.getInventario());
            wrapper.setInventario(inventarioResponse);
            String objectWrapper = objectMapper.writeValueAsString(wrapper);
            kafkaTemplate.send("mensajes", objectWrapper);
            return wrapper;
        } catch (ArgumentoInvalidoException e) {
            throw new ArgumentoInvalidoException(e.getMessage());
        }catch (JpaExcepcion jex){
            throw new JpaExcepcion(jex.getMessage());
        }catch (Exception ex){
            throw new IllegalStateException(ex);
        }
    }
}
