package com.desafio.dbc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AutorizaService {

	@Autowired
	private AssociadoAutorizado associadoAutorizado;
	
	@GetMapping("users/{cpf}")
	public ResponseEntity<ClientRetornoAssociado> getAutorizacao(@PathVariable String cpf){
		log.info("Iniciando consulta autorização para o CPF " + cpf);
		ClientRetornoAssociado associado = associadoAutorizado.verificaAutorizacaoAssociado(cpf);
		return associado != null ? ResponseEntity.ok().body(associado) : ResponseEntity.notFound().build(); 
    }
	
}
