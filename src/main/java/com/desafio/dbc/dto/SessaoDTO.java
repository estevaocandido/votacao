package com.desafio.dbc.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "SessaoDTO", description = "Objeto contendo os dados para abertura de uma Sessão.")
public class SessaoDTO {
	
	@ApiModelProperty(value = "Campo obrigatório contendo o idêntificador da Pauta.", required = true)
	private Long pautaId;
	
	@ApiModelProperty(value = "Campo contendo a data e hora do encerramento de uma Sessão, formato yyyy-MM-dd'T'HH:mm:ss.")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime finalSessao;

}
