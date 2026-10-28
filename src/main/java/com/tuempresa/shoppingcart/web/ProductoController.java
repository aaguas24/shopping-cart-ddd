package com.tuempresa.shoppingcart.web;

import com.tuempresa.shoppingcart.application.catalog.dto.PaginacionDTO;
import com.tuempresa.shoppingcart.application.catalog.dto.ProductoDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.ConsultarProductoUseCase;
import com.tuempresa.shoppingcart.application.catalog.usecase.impl.RegistrarProductoAndInventarioUseCaseImpl;
import com.tuempresa.shoppingcart.application.catalog.wrapper.ProductoAndInventarioWrapper;
import com.tuempresa.shoppingcart.shared.enums.OrderPage;
import com.tuempresa.shoppingcart.shared.exception.JpaExcepcion;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/producto")
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
    public ResponseEntity<Page<ProductoDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "5") int size,
                                                     @RequestParam(name ="sort", required = false) String sort,
                                                     @RequestParam(name ="order", required = false) OrderPage order){


        PaginacionDTO paginacion= new PaginacionDTO(page, size, sort, order);
        return ResponseEntity.status(HttpStatus.OK).body(consultarProductoUseCase.findAll(paginacion));
    }
}

