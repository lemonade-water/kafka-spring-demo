package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public CustomRebalanceListener customRebalanceListener() {
        return new CustomRebalanceListener();
    }
}