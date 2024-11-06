package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        //配置消息发送异常回调，当消息发送失败时，为保证消息的可靠性应该在消息回调保存到数据库中，这里只做日志记录。
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
                ProducerListener.super.onSuccess(producerRecord, recordMetadata);
                log.info("发送消息成功：");
            }

            @Override
            public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                ProducerListener.super.onError(producerRecord, recordMetadata, exception);
                log.error("发送消息失败：");
            }
        });
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("test-topic-1", message);
        future.exceptionally((throwable) -> {
            //有抛出异常作为参数，传递到回调方法
            log.error("exceptionally！！");
            return null;
        });
        future.thenAccept((sendResult) -> {
            log.info("thenAccept：" + sendResult.getProducerRecord().partition());
        });
    }
}
