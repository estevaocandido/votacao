package com.desafio.dbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dbc.dto.SessaoDTO;
import com.desafio.dbc.exception.BusinesException;
import com.desafio.dbc.model.Sessao;
import com.desafio.dbc.service.SessaoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/sessao")
public class SessaoController {
	
	@Autowired
	private SessaoService sessaoService;
	
	@PostMapping
	public ResponseEntity<Sessao> openSessao(@RequestBody SessaoDTO sessao) {
		return new ResponseEntity<>(sessaoService.openSessao(sessao), HttpStatus.OK);
	}
	
	@ExceptionHandler(BusinesException.class)
	public ResponseEntity<JsonNode> handleException(BusinesException e) {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("status", HttpStatus.BAD_REQUEST.value());
		objectNode.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
	}

}
