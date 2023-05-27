package com.residencia.trabalho_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.trabalho_final.entites.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	Produto findByDescricao(String descricao);
}
