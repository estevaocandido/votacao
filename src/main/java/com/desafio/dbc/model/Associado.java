package com.desafio.dbc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;


@Entity
@Getter
@ToString
public class Associado implements Serializable{
	
	private static final long serialVersionUID = 6374930583020468502L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricula;

	@NotNull
	private String nome;
	
	@NotNull
	@Column(length = 11)
	private String cpf;

}
