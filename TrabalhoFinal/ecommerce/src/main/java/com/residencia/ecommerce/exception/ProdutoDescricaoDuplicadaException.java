package com.residencia.ecommerce.exception;

public class ProdutoDescricaoDuplicadaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ProdutoDescricaoDuplicadaException() {
        super("Não é possivel cadastrar outro produto com a mesma descrição.");
    }
}

