package com.tuempresa.shoppingcart.domain.email.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificacionEmail {
    private String emailDestino;
    private String asunto;
    private String mensaje;
}
