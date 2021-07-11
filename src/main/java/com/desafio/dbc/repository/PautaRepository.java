package com.desafio.dbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.dbc.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

	List<Pauta> findByResultadoIsNull();
	
}
