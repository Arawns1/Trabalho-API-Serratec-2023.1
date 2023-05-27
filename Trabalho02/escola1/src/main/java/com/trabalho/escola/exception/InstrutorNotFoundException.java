package com.trabalho.escola.exception;

public class InstrutorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InstrutorNotFoundException(Integer idInstrutor) {

		super ("Não foi encontrado o ID " + idInstrutor);
	}
}