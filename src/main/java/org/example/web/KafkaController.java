package org.example.web;

import jakarta.annotation.Resource;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.config.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Resource
    private KafkaProducer kafkaProducer;

    /**
     * 测试发送消息
     * @param message
     * @return
     */
    @GetMapping("/sendMessage")
    public String sendMessage(String message) {
        kafkaProducer.sendMessage(message);
        return "success";
    }

}

