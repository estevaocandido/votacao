package com.desafio.dbc.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.desafio.dbc.dto.SessaoDTO;
import com.desafio.dbc.model.Pauta;
import com.desafio.dbc.model.Sessao;
import com.desafio.dbc.service.SessaoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@WebMvcTest(SessaoController.class)
class SessaoControllerTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private SessaoService sessaoService;
	
	@Test
	void deve_abrir_nova_sessao() throws JsonProcessingException, Exception {
		
		LocalDateTime now = LocalDateTime.now();
		
		when(this.sessaoService.openSessao(SessaoDTO.builder().pautaId(1L).finalSessao(now).build()))
			.thenReturn(Sessao.builder().pauta(Pauta.builder().pautaId(1L).descricao("").build()).inicioSessao(now).finalSessao(now).build());
		
		
		String body = "{\r\n" + 
		 		"    \"pautaId\" : 1,\r\n" + 
		 		"    \"finalSessao\" : \"2021-07-12T12:23:00\"\r\n" + 
		 		"}";
		 
		 mock.perform(post("/sessao/v1/open")
				 .content(body)
				 .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		 
	}
	
}
