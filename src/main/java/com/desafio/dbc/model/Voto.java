package com.desafio.dbc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.desafio.dbc.enums.EnumVoto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "voto")
@Validated
public class Voto implements Serializable{

	private static final long serialVersionUID = 2483855721954727226L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long votoId;

	@ManyToOne
	@JoinColumn(name = "sessao_id")
	@NotNull
	@JsonIgnore
    private Sessao sessao;

	@ManyToOne()
	@JoinColumn(name = "matricula")
	@NotNull
	@JsonIgnore
	private Associado associado;
	
	@JsonIgnore
	private EnumVoto voto;

}
