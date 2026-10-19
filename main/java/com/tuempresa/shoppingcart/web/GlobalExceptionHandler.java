package com.tuempresa.shoppingcart.web;


import com.tuempresa.shoppingcart.shared.exception.ArgumentoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArgumentoInvalidoException.class)
    public ProblemDetail argumentosInvalidos(ArgumentoInvalidoException argumentos){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setType(URI.create("http://localhost:8080/error/409.html"));
        problem.setTitle(argumentos.getClass().getSimpleName());
        problem.setDetail(argumentos.getMessage());
        problem.setProperty("fecha", LocalDateTime.now());
        return problem;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validacionesCapaAplicacion(MethodArgumentNotValidException methodArgumentNotValidException){
        ProblemDetail detail= ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle(methodArgumentNotValidException.getClass().getSimpleName());
        detail.setDetail(methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
        detail.setProperty("campoError",methodArgumentNotValidException.getBindingResult().getFieldError().getField());
        detail.setProperty("fecha", LocalDateTime.now());
        return detail;
    }

}

