package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "test-topic-1", groupId = "my-consumer-group")
    public void listen(@Payload List<ConsumerRecord<String, String>> list, @Header Acknowledgment ack) {
        try {
            list.forEach(i -> {
                String message = i.value();
                log.info("接受消息成功1：" + message);
            });
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        ack.acknowledge();
    }
}
