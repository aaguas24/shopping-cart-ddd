package com.tuempresa.shoppingcart.domain.email.repository;

import com.tuempresa.shoppingcart.domain.email.entity.NotificacionEmail;

public interface NotificacionEmailPort {
    public void notificar(NotificacionEmail notificacionEmail);
}
