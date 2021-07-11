package com.desafio.dbc.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desafio.dbc.service.PautaService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class dbcSchedule {

	@Autowired
	private PautaService pautaService;
	
	@Scheduled(cron = "15 * * * * *")
	public void resultPauta() {
		log.info("Inicio schedule resultado de pauta");
		pautaService.findByResultadoIsNull().forEach(p -> pautaService.getResultado(p));
	}
	
}
