package com.desafio.dbc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "pauta")
@ApiModel(value = "Pauta", description = "Objeto com as informações da Pauta.")
public class Pauta implements Serializable{

	private static final long serialVersionUID = 942217544630798469L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pauta_id")
	@ApiModelProperty(value = "Campo obrigatório idêntificador da Pauta", required = true)
	private Long pautaId;
	
	@NotNull
	@ApiModelProperty(value = "Campo obrigatório contendo a descrição/assunto da Pauta", required = true)
	private String descricao;
	
	@ApiModelProperty(value = "Campo contendo o resultado da Pauta")
	private String resultado;

}
