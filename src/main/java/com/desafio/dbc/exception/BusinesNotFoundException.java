package com.desafio.dbc.exception;

public class BusinesNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4013419319347678669L;
	
	public BusinesNotFoundException(String menssage) {
		super(menssage);
	}

}
