package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDemoApp {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(KafkaDemoApp.class,args);
    }
}