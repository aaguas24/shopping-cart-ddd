package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarInventarioUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoAndInventarioUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarProductoUseCase;
import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import com.tuempresa.shoppingcart.domain.catalog.service.strategy.ValidarExistenciaCategoriaStategy;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrarProductoAndInventarioUseCaseImpl implements RegistrarProductoAndInventarioUseCase {

    private final RegistrarProductoUseCase registrarProductoUseCase;
    private final RegistrarInventarioUseCase registrarInventarioUseCase;
    private final ValidarExistenciaCategoriaStategy validarExistenciaCategoriaStategy;
    private final RabbitTemplate rabbitTemplate;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public RegistrarProductoAndInventarioUseCaseImpl(RegistrarProductoUseCase registrarProductoUseCase,
                                                     RegistrarInventarioUseCase registrarInventarioUseCase,
                                                     @Qualifier("ValidarExistenciaCategoria") ValidarExistenciaCategoriaStategy validarExistenciaCategoriaStategy,
                                                     RabbitTemplate rabbitTemplate, JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.registrarProductoUseCase = registrarProductoUseCase;
        this.registrarInventarioUseCase = registrarInventarioUseCase;
        this.validarExistenciaCategoriaStategy = validarExistenciaCategoriaStategy;
        this.rabbitTemplate = rabbitTemplate;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional(rollbackFor =  JpaExcepcion.class)
    public ProductoAndInventarioWrapper registrar(ProductoAndInventarioWrapper productoAndInventarioWrapper) throws JpaExcepcion {
        try {
            ProductoAndInventarioWrapper wrapper = new ProductoAndInventarioWrapper();
            validarExistenciaCategoriaStategy.validar(productoAndInventarioWrapper.getProducto().getIdCategoria());
            var productosResponse = registrarProductoUseCase.registrar(productoAndInventarioWrapper.getProducto());
            wrapper.setProducto(productosResponse);
            productoAndInventarioWrapper.getInventario().setIdProducto(productosResponse.getId());
            var inventarioResponse= registrarInventarioUseCase.registrar(productoAndInventarioWrapper.getInventario());
            wrapper.setInventario(inventarioResponse);
            String objectWrapper = objectMapper.writeValueAsString(wrapper);
            jmsTemplate.convertAndSend("producto.registro", objectWrapper);
//            rabbitTemplate.convertAndSend("NOTIFICAR_EXCHANGE","producto.registrado",wrapper);
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
