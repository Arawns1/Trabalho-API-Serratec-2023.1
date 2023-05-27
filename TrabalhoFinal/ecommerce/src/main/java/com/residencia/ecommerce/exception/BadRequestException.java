package com.residencia.ecommerce.exception;

public class BadRequestException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String entidade, Integer id) {
        super("Ja existe um/a " + entidade + "com alguma dessas informações.");
    }

}

