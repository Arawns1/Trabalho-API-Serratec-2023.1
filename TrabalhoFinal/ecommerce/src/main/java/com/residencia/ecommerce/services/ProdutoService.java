package com.residencia.ecommerce.services;

import java.util.Date;
import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.exception.ProdutoDescricaoDuplicadaException;
import com.residencia.ecommerce.exception.ProdutoNotFoundException;
import com.residencia.ecommerce.repositories.CategoriaRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;

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
		return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
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
		return produtoDeletada == null;
	}

	// ---------------//
	// DTOs 
	// ----------------//

	public ProdutoDTO getProdutoDTOById(Integer id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
		return modelMapper.map(produto, ProdutoDTO.class);
	}

	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setImagem(produtoDTO.getImagem());
		produto.setValorUnitario(produtoDTO.getValorUnitario());

		Produto produtoExistente = produtoRepository.findByDescricao(produto.getDescricao());
		if (produtoExistente != null) {
			throw new ProdutoDescricaoDuplicadaException();
		}

		Categoria categoria = categoriaRepository.findById(produtoDTO.getIdCategoria())
				.orElseThrow(() -> new NoSuchElementException("Categoria", produtoDTO.getIdCategoria()));

		produto.setCategoria(categoria);
		
		if (produto.getQtdEstoque() == null) {
			produto.setQtdEstoque(0);
		}

		produto.setQtdEstoque(produto.getQtdEstoque() + 1);
		produto.setDataCadastro(Date.from(Instant.now()));

		produtoRepository.save(produto);
		return modelMapper.map(produto, ProdutoDTO.class);
	}
}
