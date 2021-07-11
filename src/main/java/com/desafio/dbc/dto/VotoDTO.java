package com.desafio.dbc.dto;

import com.desafio.dbc.enums.EnumVoto;

import lombok.Getter;

@Getter
public class VotoDTO {

	private Long sessaoId;
	private Long matricula;
	private EnumVoto voto;
}
