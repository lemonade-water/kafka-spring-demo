package org.example.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("test-topic-1", message);
        future.thenAccept(result -> log.info("发送消息成功：" + result))
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    log.error("发送消息失败：" + throwable.getMessage()); return null;
                });
    }
}
