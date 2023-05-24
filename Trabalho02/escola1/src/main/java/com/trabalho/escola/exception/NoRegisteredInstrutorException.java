package com.trabalho.escola.exception;

public class NoRegisteredInstrutorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoRegisteredInstrutorException() {
		super ("Não há nenhum instrutor cadastrado.");
		
	}
	
}
