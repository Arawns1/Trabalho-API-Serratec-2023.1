package com.residencia.trabalho_final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.trabalho_final.entites.Produto;
import com.residencia.trabalho_final.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;	
	
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto updateProduto(Produto produto, Integer id) {
		return produtoRepository.save(produto);
	}
	
	public Boolean deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
		Produto produtoDeletada = produtoRepository.findById(id).orElse(null);
		if(produtoDeletada == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
