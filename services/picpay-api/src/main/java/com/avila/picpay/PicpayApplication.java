package com.avila.picpay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PicpayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PicpayApplication.class, args);
    }
}