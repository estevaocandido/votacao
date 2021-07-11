package com.desafio.dbc.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.dbc.enums.EnumVoto;
import com.desafio.dbc.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

	@Query("SELECT v FROM Voto v WHERE v.sessao.pauta.pautaId = ?1 and v.associado.matricula = ?2")
	Optional<List<Voto>> findByAssociadoAndSessao(Long pautaId, Long matricula);

	@Query("SELECT COUNT(v) FROM Voto v WHERE v.sessao.pauta.pautaId = ?1 and v.voto = ?2")
	BigDecimal getVotos(Long pautaId, EnumVoto voto);
	
}
