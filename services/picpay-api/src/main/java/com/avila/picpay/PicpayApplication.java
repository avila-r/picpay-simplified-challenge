package com.avila.picpay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class PicpayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PicpayApplication.class, args);
    }
}