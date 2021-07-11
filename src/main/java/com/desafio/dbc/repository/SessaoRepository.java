package com.desafio.dbc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.dbc.model.Pauta;
import com.desafio.dbc.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>{

	Optional<Sessao> findByPauta(Pauta pauta);

	@Query("SELECT s FROM Sessao s WHERE s.pauta.resultado is not null")
	List<Sessao> getSessaoPautaResultadoNull();

}
