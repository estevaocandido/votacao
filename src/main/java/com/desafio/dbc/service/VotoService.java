package com.desafio.dbc.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.desafio.dbc.client.AutorizaAssociadoClient;
import com.desafio.dbc.client.ClientRetornoAssociado;
import com.desafio.dbc.constants.Constants;
import com.desafio.dbc.dto.VotoDTO;
import com.desafio.dbc.enums.EnumVoto;
import com.desafio.dbc.exception.BusinesException;
import com.desafio.dbc.exception.BusinesNotAutorizationException;
import com.desafio.dbc.model.Associado;
import com.desafio.dbc.model.Sessao;
import com.desafio.dbc.model.Voto;
import com.desafio.dbc.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private AssociadoService associadoService;
	
	@Autowired
	private AutorizaAssociadoClient autorizaAssociadoClient;

	public Voto votar(VotoDTO voto) {
		
		Optional<Associado> associado = associadoService.findByMatricula(voto.getMatricula());
		
		if(!associado.isPresent()) throw new BusinesException(Constants.ASSOCIADO_NOT_FOUND);
		
		Optional<Sessao> sessao = sessaoService.findById(voto.getSessaoId());
		if(!sessao.isPresent()) throw new BusinesException(Constants.SESSAO_NOT_FOUND);
		
		ResponseEntity<ClientRetornoAssociado> autorizacao = autorizaAssociadoClient.verificarCpf(associado.get().getCpf());

		if(autorizacao.getStatusCode().equals(HttpStatus.OK) &&  autorizacao.getBody().getStatus().equals(Constants.AUTORIZADO)) {
			
			log.info("Associado {} tem o voto {}", associado.get(), autorizacao.getBody());
			
			Optional<List<Voto>> votos = votoRepository.findByAssociadoAndSessao(sessao.get().getPauta().getPautaId(), associado.get().getMatricula());
			
			if(votos.isPresent() && !votos.get().isEmpty()) throw new BusinesException(Constants.VOTO_EXIST);
			
			if(sessao.get().getInicioSessao().isAfter(LocalDateTime.now()) && sessao.get().getFinalSessao().isBefore(LocalDateTime.now())) {
				throw new BusinesException(Constants.OUT_OF_TIME_SESSAO);
			}
		} else {
			log.info("Não foi autorizado o voto do Associado {} ", associado.get());
			throw new BusinesNotAutorizationException(Constants.NOT_AUTORIZATION);
		}
		
		
		return votoRepository.save(Voto.builder().associado(associado.get()).sessao(sessao.get()).voto(voto.getVoto()).build());
	}

	public BigDecimal getVotos(Long pautaId, EnumVoto voto) {
		return votoRepository.getVotos(pautaId, voto);
	}
	
	
	
	
}
