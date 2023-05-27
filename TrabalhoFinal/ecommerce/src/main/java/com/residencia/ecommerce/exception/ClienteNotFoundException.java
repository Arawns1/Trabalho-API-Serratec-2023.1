package com.residencia.ecommerce.exception;

public class ClienteNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ClienteNotFoundException(Integer id_cliente) {
		super ("Não foi encontrado o produto de ID: " + id_cliente);
		
	}
}

