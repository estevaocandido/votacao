package com.desafio.dbc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "PautaDTO", description = "Objeto contendo a descrição para criação de uma nova pauta.")
public class PautaDTO {

	@ApiModelProperty(value = "Campo obrigatório contendo a descrição da Pauta.", required = true)
	private String descricao;
	
}
