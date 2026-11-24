package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarInventarioUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoAndInventarioUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoUseCase;
import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import com.tuempresa.shoppingcart.infraestructure.config.KafkaProperties;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RegistrarProductoAndInventarioUseCaseImpl implements RegistrarProductoAndInventarioUseCase {

    private final RegistrarProductoUseCase registrarProductoUseCase;
    private final RegistrarInventarioUseCase registrarInventarioUseCase;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public RegistrarProductoAndInventarioUseCaseImpl(RegistrarProductoUseCase registrarProductoUseCase,
                                                     RegistrarInventarioUseCase registrarInventarioUseCase, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, KafkaProperties kafkaProperties) {
        this.registrarProductoUseCase = registrarProductoUseCase;
        this.registrarInventarioUseCase = registrarInventarioUseCase;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductoAndInventarioWrapper registrar(String data, List<MultipartFile> imagen) {
        try {
            ProductoAndInventarioWrapper productoAndInventarioWrapper = objectMapper.readValue(data, ProductoAndInventarioWrapper.class);
            productoAndInventarioWrapper.setImagen(getListBytes(imagen));
            var productoResponse = registrarProductoUseCase.registrar(productoAndInventarioWrapper.getProducto());
            productoAndInventarioWrapper.setProducto(productoResponse);
            productoAndInventarioWrapper.getInventario().setIdProducto(productoResponse.getId());
            var inventarioResponse = registrarInventarioUseCase.registrar(productoAndInventarioWrapper.getInventario());
            productoAndInventarioWrapper.setInventario(inventarioResponse);
            String objectWrapper = objectMapper.writeValueAsString(productoAndInventarioWrapper);
            kafkaTemplate.send(kafkaProperties.getProductoRegistrado(), objectWrapper);
            return productoAndInventarioWrapper;
        } catch (ArgumentoInvalidoException e) {
            throw e;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static @NonNull List<byte[]> getListBytes(List<MultipartFile> imagen) {

        return imagen.stream().filter(Objects::nonNull).map(multi -> {
            try {
                return multi.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
