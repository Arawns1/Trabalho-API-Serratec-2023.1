package com.residencia.trabalho_final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.trabalho_final.entites.Produto;
import com.residencia.trabalho_final.exception.ProdutoDescricaoDuplicadaException;
import com.residencia.trabalho_final.exception.ProdutoNotFoundException;
import com.residencia.trabalho_final.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;	
	
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	public Produto getProdutoById(Integer id) {

		return produtoRepository.findById(id).orElseThrow(()-> new ProdutoNotFoundException(id));
	}
	
	public Produto saveProduto(Produto produto) {
		Produto produtoExistente = produtoRepository.findByDescricao(produto.getDescricao());
		if (produtoExistente != null) {
			throw new ProdutoDescricaoDuplicadaException();
		}
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
