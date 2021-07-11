package com.desafio.dbc.factory;

import java.time.LocalDateTime;
import java.util.Objects;

import com.desafio.dbc.dto.SessaoDTO;
import com.desafio.dbc.model.Pauta;
import com.desafio.dbc.model.Sessao;

public class SessaoFactory {

	public Sessao sessaoDTOToSessao(SessaoDTO sessao, Pauta pauta, LocalDateTime inicioSessao) {
		return Sessao.builder().inicioSessao(inicioSessao).pauta(pauta)
				.finalSessao(getFinalSessao(sessao.getFinalSessao(), inicioSessao)).build();
	}

	private LocalDateTime getFinalSessao(LocalDateTime finalSessao, LocalDateTime inicioSessao) {
		if(Objects.isNull(finalSessao)) {
			return inicioSessao.plusMinutes(1);
		}
		return finalSessao;
	}

}
