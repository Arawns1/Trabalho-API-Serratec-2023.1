package com.residencia.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entites.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	Produto findByDescricao(String descricao);

}