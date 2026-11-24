package com.tuempresa.shoppingcart.infraestructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaTopicConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    KafkaAdmin.NewTopics mensajesTopic(KafkaProperties kafkaProperties) {
        if (kafkaProperties.getTopics() == null || kafkaProperties.getTopics().isEmpty()) {
            return new KafkaAdmin.NewTopics();
        }
        NewTopic[] topics = kafkaProperties.getTopics().stream()
                .map(t -> TopicBuilder.name(t.getName()).replicas(t.getReplicas()).partitions(t.getPartitions()).build())
                .toArray(NewTopic[]::new);

        return new KafkaAdmin.NewTopics(topics);
    }

}
