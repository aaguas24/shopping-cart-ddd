package com.tuempresa.shoppingcart.infraestructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    NewTopic mensajesTopic() {
        return TopicBuilder.name("mensajes")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
