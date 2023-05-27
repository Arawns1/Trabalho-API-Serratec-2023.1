package com.residencia.trabalho_final.services;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.trabalho_final.DTO.ProdutoDTO;
import com.residencia.trabalho_final.entites.Categoria;
import com.residencia.trabalho_final.entites.Produto;
<<<<<<< Updated upstream
=======
import com.residencia.trabalho_final.exception.NoSuchElementException;
import com.residencia.trabalho_final.exception.ProdutoDescricaoDuplicadaException;
import com.residencia.trabalho_final.exception.ProdutoNotFoundException;
import com.residencia.trabalho_final.repositories.CategoriaRepository;
>>>>>>> Stashed changes
import com.residencia.trabalho_final.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ModelMapper modelMapper;	
	
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto saveProduto(Produto produto) {
<<<<<<< Updated upstream
=======
		Produto produtoExistente = produtoRepository.findByDescricao(produto.getDescricao());
		if (produtoExistente != null) {
			throw new ProdutoDescricaoDuplicadaException();
		}
>>>>>>> Stashed changes
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
	
		// ---------------//
		//	    DTOs	  //
		//----------------//
	
		public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
			Produto produto = new Produto();
			produto.setNome(produtoDTO.getNome());
			produto.setDescricao(produtoDTO.getDescricao());
			produto.setImagem(produtoDTO.getImagem());
			produto.setValorUnitario(produtoDTO.getValorUnitario());
			
			Categoria categoria = categoriaRepository.findById(produtoDTO.getIdCategoria())
								  .orElseThrow(() -> new NoSuchElementException("Categoria", produtoDTO.getIdCategoria()));
			
			produto.setCategoria(categoria);
			System.out.println(categoria);
			
			if(produto.getQtdEstoque() == null) {
				produto.setQtdEstoque(0);
			}
			
			produto.setQtdEstoque(produto.getQtdEstoque() + 1);
			produto.setDataCadastro(Date.from(Instant.now()));
			
			produtoRepository.save(produto);
			return modelMapper.map(produto, ProdutoDTO.class);
		}
}
