package com.residencia.trabalho_final.exception;

public class ProdutoDescricaoDuplicadaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ProdutoDescricaoDuplicadaException() {
        super("Não e possivel cadastras outro produto com a mesma descrição.");
    }
}