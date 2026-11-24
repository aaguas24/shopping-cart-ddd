package com.tuempresa.shoppingcart.domain.catalog.repository;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository {

    public Page<Producto> findAll(Pageable pageable);
    public Optional<Producto> findById(UUID id);
    public Optional<Producto> save(Producto producto);
    public Boolean existById(UUID id);
    public Boolean existByIdOrCodigoSku(String codigoSku);
    public void updateImages(Producto producto);
}
