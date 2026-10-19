package com.tuempresa.shoppingcart.application.catalog.usecase.impl;

import com.tuempresa.shoppingcart.application.catalog.dto.InventarioDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarInventarioUseCase;
import com.tuempresa.shoppingcart.domain.catalog.repository.InventarioRepository;
import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import com.tuempresa.shoppingcart.shared.mapper.InventarioMapper;
import org.springframework.stereotype.Service;

@Service
public class RegistrarInventarioUseCaseImpl implements RegistrarInventarioUseCase {

    private final InventarioRepository inventarioRepository;

    private final InventarioMapper inventarioMapper;

    public RegistrarInventarioUseCaseImpl(InventarioRepository inventarioRepository, InventarioMapper inventarioMapper) {
        this.inventarioRepository = inventarioRepository;
        this.inventarioMapper = inventarioMapper;
    }

    @Override
    public InventarioDTO registrar(InventarioDTO inventarioDTO) throws JpaExcepcion {
        try {
            var inventarioDomain = inventarioMapper.toDomain(inventarioDTO);
            var inventarioResponse = inventarioRepository.save(inventarioDomain);
            return inventarioMapper.toDto(inventarioResponse);
        } catch (ArgumentoInvalidoException aex) {
            throw new ArgumentoInvalidoException("Error al registrar el inventario: " + aex.getMessage());
        } catch (JpaExcepcion jex) {
            throw new JpaExcepcion("Error al registrar el inventario: " + jex.getMessage());
        } catch (Exception ex) {
            throw new IllegalStateException("Error inesperado al registrar el inventario: " + ex.getMessage(), ex);
        }
    }
}
