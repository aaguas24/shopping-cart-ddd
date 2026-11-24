package com.tuempresa.shoppingcart.application.catalog.usecase;

import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RegistrarProductoAndInventarioUseCase {
    ProductoAndInventarioWrapper registrar(String data, List<MultipartFile> imagen);
}
