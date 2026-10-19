package com.tuempresa.shoppingcart.infraestructure.email.adapter;

import com.tuempresa.shoppingcart.domain.email.entity.NotificacionEmail;
import com.tuempresa.shoppingcart.domain.email.repository.NotificacionEmailPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class NotificacionEmailAdapter implements NotificacionEmailPort {
    private final JavaMailSender javaMailSender;

    public NotificacionEmailAdapter(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void notificar(NotificacionEmail notificacionEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notificacionEmail.getEmailDestino());
        message.setSubject(notificacionEmail.getAsunto());
        message.setText(notificacionEmail.getMensaje());
        javaMailSender.send(message);
    }
}
