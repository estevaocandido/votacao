package com.desafio.dbc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dbc.model.Associado;
import com.desafio.dbc.repository.AssociadoRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository associadoRepository;

	public Optional<Associado> findByMatricula(Long matricula) {
		log.info("Busca Associado por matricula {}" , matricula);
		return associadoRepository.findById(matricula);
	}
	
	
}
