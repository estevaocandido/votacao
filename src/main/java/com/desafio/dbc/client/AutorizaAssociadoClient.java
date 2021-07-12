package com.desafio.dbc.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AutorizaAssociadoClient {

	private static final String URL = "https://user-info.herokuapp.com/users/";
	
	public ResponseEntity<ClientRetornoAssociado> verificarCpf(String cpf) {
		log.info("Iniciando consulta autorização para o CPF " + cpf);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity(URL.concat(cpf) , ClientRetornoAssociado.class);
	}
}
