package com.desafio.dbc.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dbc.model.Pauta;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ProducerMq {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queue;
	
	public void send(Pauta result) {
		log.info("Enviando a Pauta {} para fila {}" , result, this.queue.getName());
		rabbitTemplate.convertAndSend(this.queue.getName(), result);
	}

}
