package com.tuempresa.shoppingcart.web;

import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.ConsultarProductoUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.impl.RegistrarProductoAndInventarioUseCaseImpl;
import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/producto/")
public class ProductoController {
    private final RegistrarProductoAndInventarioUseCaseImpl registrarProductoAndInventarioUseCase;
    private final ConsultarProductoUseCase consultarProductoUseCase;
    public ProductoController(RegistrarProductoAndInventarioUseCaseImpl registrarProductoAndInventarioUseCase, ConsultarProductoUseCase consultarProductoUseCase){
        this.registrarProductoAndInventarioUseCase =registrarProductoAndInventarioUseCase;
        this.consultarProductoUseCase = consultarProductoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductoAndInventarioWrapper> registrar(@Valid @RequestBody ProductoAndInventarioWrapper productoAndInventarioWrapper) throws JpaExcepcion {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrarProductoAndInventarioUseCase.registrar(productoAndInventarioWrapper));
    }
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(consultarProductoUseCase.findAll());
    }
}

