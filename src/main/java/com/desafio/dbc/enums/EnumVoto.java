package com.desafio.dbc.enums;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum EnumVoto implements EnumType<Serializable>{

	SIM("S", true), 
	NAO("N", false);

    private String voto;
	private boolean value;
	
	
	EnumVoto(String voto, boolean value) {
		this.voto = voto;
		this.value = value;
	}

	@Override
	public Serializable getId() {
		return value;
	}
	
	public static EnumVoto fromValue(String votoAtual) {
	    for(EnumVoto voto: EnumVoto.values()) {
	        if(voto.getVoto().equals(votoAtual)) {
	            return voto;
	        }
	    }
	    return null;
	}

}
