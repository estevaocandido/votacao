package com.desafio.dbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dbc.dto.PautaDTO;
import com.desafio.dbc.exception.BusinesNotFoundException;
import com.desafio.dbc.model.Pauta;
import com.desafio.dbc.service.PautaService;
import com.desafio.dbc.swagger.ConstantsSwagger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/pauta/v1")
@Api(tags = { "Pauta" })
public class PautaController {
	
	@Autowired
	private PautaService pautaService;
	
	@ApiOperation(value = "Busca todas as pautas existentes.", notes = "Busca todas as pautas que existem no banco.", hidden = false)
	@GetMapping("/all")
    @ApiResponses({ @ApiResponse(code = 200, message = ConstantsSwagger.BUSCA_EFETUADA_COM_SUCESSO),
        @ApiResponse(code = 400, message = ConstantsSwagger.ERRO_400),
        @ApiResponse(code = 500, message = ConstantsSwagger.ERRO_500) })
	public ResponseEntity<List<Pauta>> findAll(){
		List<Pauta> list = pautaService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca pauta por id.", notes = "Busca uma pauta específica pelo id.", hidden = false)
    @ApiResponses({ @ApiResponse(code = 200, message = ConstantsSwagger.BUSCA_EFETUADA_COM_SUCESSO),
        @ApiResponse(code = 400, message = ConstantsSwagger.ERRO_400),
        @ApiResponse(code = 500, message = ConstantsSwagger.ERRO_500) })
	public ResponseEntity<Pauta> getPauta(@PathVariable Long id) {
		return ResponseEntity.ok().body(pautaService.getPautaById(id));
	}
	
	@ExceptionHandler(BusinesNotFoundException.class)
	public ResponseEntity<JsonNode> handleException(BusinesNotFoundException e) {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("status", HttpStatus.NOT_FOUND.value());
		objectNode.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectNode);
	}
	
	@PostMapping("/create")
	@ApiOperation(value = "Criando Pauta.", notes = "Cria uma nova pauta", hidden = false)
    @ApiResponses({ @ApiResponse(code = 201, message = ConstantsSwagger.PAUTA_CRIADA_COM_SUCESSO),
        @ApiResponse(code = 400, message = ConstantsSwagger.ERRO_400),
        @ApiResponse(code = 500, message = ConstantsSwagger.ERRO_500) })
	public ResponseEntity<Pauta> createPauta(@RequestBody PautaDTO pauta){
		try {
			return new ResponseEntity<>(pautaService.createPauta(pauta), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
