package com.residencia.trabalho_final.exception;

public class ClienteCpfDuplicadoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ClienteCpfDuplicadoException() {
        super("Este cpf já esta cadastrado.");
    }
}
