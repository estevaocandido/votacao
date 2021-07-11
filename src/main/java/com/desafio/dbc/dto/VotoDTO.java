package com.desafio.dbc.dto;

import com.desafio.dbc.enums.EnumVoto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "VotoDTO", description = "Objeto contendo os dados para realizar uma votação.")
public class VotoDTO {

	@ApiModelProperty(value = "Campo obrigatório contendo o idêntificador da sessão.", required = true)
	private Long sessaoId;
	
	@ApiModelProperty(value = "Campo obrigatório contendo o idêntificador do associado.", required = true)
	private Long matricula;
	
	@ApiModelProperty(value = "Campo obrigatório contendo o voto.", required = true)
	private EnumVoto voto;
}
