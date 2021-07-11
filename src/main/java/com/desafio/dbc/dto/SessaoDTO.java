package com.desafio.dbc.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;

@Getter
public class SessaoDTO {
	
	private Long pautaId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime finalSessao;

}
