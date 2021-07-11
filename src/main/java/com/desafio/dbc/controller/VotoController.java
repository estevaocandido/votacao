package com.desafio.dbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dbc.constants.Constants;
import com.desafio.dbc.dto.VotoDTO;
import com.desafio.dbc.exception.BusinesException;
import com.desafio.dbc.exception.BusinesNotAutorizationException;
import com.desafio.dbc.service.VotoService;
import com.desafio.dbc.swagger.ConstantsSwagger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/voto/v1")
@Api(tags = { "Voto" })
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping("/votar")
	@ApiOperation(value = "Realiza o voto.", notes = "Realiza o voto.", hidden = false)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = ConstantsSwagger.VOTO_EFETIVADO_COM_SUCESSO),
		    @ApiResponse(code = 400, message = ConstantsSwagger.ERRO_400),
		    @ApiResponse(code = 403, message = ConstantsSwagger.NOT_AUTORIZATION),
		    @ApiResponse(code = 500, message = ConstantsSwagger.ERRO_500),
		})
	public ResponseEntity<JsonNode> votar(@RequestBody VotoDTO sessao) {
		votoService.votar(sessao);
		return ResponseEntity.status(HttpStatus.OK).body(getJsonNode());
	}
	
	@ExceptionHandler(BusinesException.class)
	public ResponseEntity<JsonNode> handleException(BusinesException e) {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("status", HttpStatus.BAD_REQUEST.value());
		objectNode.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
	}
	
	private ObjectNode getJsonNode() {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("status", HttpStatus.OK.value());
		objectNode.put("message", Constants.VOTO_SUCCESS);
		return objectNode;
	}
	
	@ExceptionHandler(BusinesNotAutorizationException.class)
	public ResponseEntity<JsonNode> handleException(BusinesNotAutorizationException e) {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("status", HttpStatus.UNAUTHORIZED.value());
		objectNode.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(objectNode);
	}
	
}
