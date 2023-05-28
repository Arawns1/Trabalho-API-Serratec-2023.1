package com.residencia.ecommerce.exception;

public class CEPNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CEPNotFoundException(String cep) {
		super ("Não foi possível encontrar o cep: " + cep);
		
	}
}

