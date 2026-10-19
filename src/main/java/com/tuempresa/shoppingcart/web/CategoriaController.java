package com.tuempresa.shoppingcart.web;

import com.tuempresa.shoppingcart.application.catalog.dto.CategoriaDTO;
import com.tuempresa.shoppingcart.application.catalog.usecase.RegistrarCategoriaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria/")
public class CategoriaController {
    private final RegistrarCategoriaUseCase registrarCategoriaUseCase;
    public CategoriaController(RegistrarCategoriaUseCase registrarCategoriaUseCase){
        this.registrarCategoriaUseCase = registrarCategoriaUseCase;
    }

    @PostMapping()
    public ResponseEntity<CategoriaDTO> registrar(@RequestBody CategoriaDTO categoriaDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(registrarCategoriaUseCase.registrar(categoriaDTO));
    }
}

