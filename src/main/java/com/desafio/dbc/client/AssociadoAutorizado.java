package com.desafio.dbc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://user-info.herokuapp.com/users/" , name = "dbc")
public interface AssociadoAutorizado {

	@GetMapping("{cpf}")
	ClientRetornoAssociado verificaAutorizacaoAssociado(@PathVariable("cpf") String cpf);
	
}
