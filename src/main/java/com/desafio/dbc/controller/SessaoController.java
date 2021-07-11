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
import com.desafio.dbc.swagger.ConstantsSwagger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/sessao/v1")
@Api(tags = { "Sessão" })
public class SessaoController {
	
	@Autowired
	private SessaoService sessaoService;
	
	@PostMapping("/open")
	@ApiOperation(value = "Abre a Sessão.", notes = "Realiza a abertura de sessão.", hidden = false)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = ConstantsSwagger.SESSAO_ABERTA_COM_SUCESSO),
		    @ApiResponse(code = 400, message = ConstantsSwagger.ERRO_400),
		    @ApiResponse(code = 500, message = ConstantsSwagger.ERRO_500),
		})
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
