package com.example.order;

import com.example.order.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class OrderApplication {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {
		//SpringApplication.run(OrderApplication.class, args);
		applicationContext = SpringApplication.run(OrderApplication.class, args);
	}

}