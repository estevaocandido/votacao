package com.desafio.dbc;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DbcApplication {
	
	@Value("${queue.desafio.pauta}")
	private String desafioPauta;

	public static void main(String[] args) {
		SpringApplication.run(DbcApplication.class, args);
	}
	
	@Bean
	public Queue queue() {
		return new Queue(desafioPauta, true);
	}

}
