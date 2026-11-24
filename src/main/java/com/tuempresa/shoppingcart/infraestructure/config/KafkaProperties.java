package com.tuempresa.shoppingcart.infraestructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.kafka")
public class KafkaProperties {
    private String productoRegistrado;
    private String productoImagenActualizado;
    private List<TopicConfig> topics= new ArrayList<>();

    @Getter
    @Setter
    public static class TopicConfig{
        private String name;
        private int partitions;
        private int replicas;
    }
}
