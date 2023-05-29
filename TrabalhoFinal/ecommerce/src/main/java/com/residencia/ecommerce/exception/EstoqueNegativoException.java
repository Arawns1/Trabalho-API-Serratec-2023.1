package com.residencia.ecommerce.exception;

public class EstoqueNegativoException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public EstoqueNegativoException(Integer id) {
        super("Erro: O estoque não pode ser negativo. Id do Produto = " + id);
    }
}
