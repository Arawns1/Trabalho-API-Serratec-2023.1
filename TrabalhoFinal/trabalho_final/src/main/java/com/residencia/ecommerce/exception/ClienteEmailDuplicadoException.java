package com.residencia.ecommerce.exception;

public class ClienteEmailDuplicadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ClienteEmailDuplicadoException() {
        super("Este Email já esta cadastrado.");
    }
}

