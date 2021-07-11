package com.desafio.dbc.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dbc.constants.Constants;
import com.desafio.dbc.dto.PautaDTO;
import com.desafio.dbc.enums.EnumVoto;
import com.desafio.dbc.exception.BusinesNotFoundException;
import com.desafio.dbc.model.Pauta;
import com.desafio.dbc.model.Sessao;
import com.desafio.dbc.mq.ProducerMq;
import com.desafio.dbc.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private VotoService votoService;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private ProducerMq producerMq;

	public List<Pauta> findAll() {
		log.info("Inicio por todas as pautas");
		List<Pauta> pautas = pautaRepository.findAll();
		pautas.forEach(p -> getResultado(p));
		log.info("Total de pautas encontradas " + pautas.size());
		return pautas;
	}

	public Pauta createPauta(PautaDTO pauta) {
		log.info("Criando pauta {} ", pauta);
		return pautaRepository.save(Pauta.builder().descricao(pauta.getDescricao()).build());
	}

	public Optional<Pauta> findById(Long pautaId) {
		return pautaRepository.findById(pautaId);
	}

	public Pauta getPautaById(Long id) {
		log.info("Buscando Pauta pelo id {} " ,id);
		Optional<Pauta> pauta = findById(id);
		if(!pauta.isPresent()) {
			log.info("Pauta id {} nao encontrada" ,id);
			throw new BusinesNotFoundException(Constants.NOT_FOUND_PAUTA);
		}
		log.info("Pauta {} encontrada" ,pauta.get());
		
		getResultado(pauta.get());
		
		return pauta.get();
	}

	public void getResultado(Pauta pauta) {
		
		Optional<Sessao> sessao = sessaoService.findByPauta(pauta);
		if(sessao.isPresent() && sessao.get().getFinalSessao().isBefore(LocalDateTime.now()) && pauta.getResultado() == null) {
			log.info("Inicio de contabilizacao de votos da Pauta {}", pauta);
			
			BigDecimal votosSim = votoService.getVotos(pauta.getPautaId(), EnumVoto.SIM);
			BigDecimal votosNao =  votoService.getVotos(pauta.getPautaId(), EnumVoto.NAO);
			
			if(votosSim.compareTo(votosNao) > 0) {
				pauta.setResultado("Aprovada");
				pautaRepository.save(pauta);
			}
			
			if(votosSim.compareTo(votosNao) < 0) {
				pauta.setResultado("Reprovado");
				pautaRepository.save(pauta);
			}
			
			if(votosSim.compareTo(votosNao) == 0) {
				pauta.setResultado("Indefinido");
				pautaRepository.save(pauta);
			}
			
			producerMq.send(pauta);
		}
	}

	public List<Pauta> findByResultadoIsNull() {
		return pautaRepository.findByResultadoIsNull();
		
	}

}
