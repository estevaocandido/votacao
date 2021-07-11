package com.desafio.dbc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sessao")
public class Sessao  implements Serializable{
	
	private static final long serialVersionUID = 1550868071974981187L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sessaoId;

	@OneToOne()
	@JoinColumn(name = "pauta_id")
	@NotNull
	private Pauta pauta;
	
	@NotNull
	private LocalDateTime inicioSessao;
	
	@NotNull
	private LocalDateTime finalSessao;
	
	@PrePersist
	void preInsert() {
		if (this.finalSessao == null) {
			this.finalSessao = inicioSessao.plusMinutes(1);
		}
	}

}
