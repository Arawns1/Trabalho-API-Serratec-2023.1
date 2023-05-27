package com.residencia.trabalho_final.exception;

public class ProdutoNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ProdutoNotFoundException(Integer id_produto) {
		super ("Não foi encontrado o produto de ID: " + id_produto);
		
	}

}
