package com.desafio.dbc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dbc.constants.Constants;
import com.desafio.dbc.dto.SessaoDTO;
import com.desafio.dbc.exception.BusinesException;
import com.desafio.dbc.factory.SessaoFactory;
import com.desafio.dbc.model.Pauta;
import com.desafio.dbc.model.Sessao;
import com.desafio.dbc.repository.SessaoRepository;

@Component
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private PautaService pautaService;
	
	private SessaoFactory sessaoFactory ;

	public Sessao openSessao(SessaoDTO sessao) {
		
		Optional<Pauta> pauta =  pautaService.findById(sessao.getPautaId());
		
		if (!pauta.isPresent())	throw new BusinesException(Constants.OPEN_SESSAO_NOT_FOUND_PAUTA);
		
		if(sessaoRepository.findByPauta(pauta.get()).isPresent()) throw new BusinesException(Constants.OPEN_SESSAO_FOUND_SESSAO);
		
		return sessaoRepository.save(getSessaoFactory().sessaoDTOToSessao(sessao, pauta.get(), LocalDateTime.now()));
	}
	
	
	private SessaoFactory getSessaoFactory() {
		if (sessaoFactory == null) {
            sessaoFactory = new SessaoFactory();
        }
        return sessaoFactory;
    }


	public Optional<Sessao> findById(Long sessaoId) {
		return sessaoRepository.findById(sessaoId);
	}


	public Optional<Sessao> findByPauta(Pauta pauta) {
		return sessaoRepository.findByPauta(pauta);
	}


	public List<Sessao> getSessaoPautaResultadoNull() {
		return sessaoRepository.getSessaoPautaResultadoNull();
		
	}
	
}
