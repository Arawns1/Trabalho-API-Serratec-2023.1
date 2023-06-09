package com.residencia.ecommerce.services;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.dto.ImagemDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.entites.Imagem;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.exception.CustomException;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.exception.ProdutoDescricaoDuplicadaException;
import com.residencia.ecommerce.exception.ProdutoNotFoundException;
import com.residencia.ecommerce.exception.UploadArquivoException;
import com.residencia.ecommerce.repositories.CategoriaRepository;
import com.residencia.ecommerce.repositories.ImagemRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ImagemRepository imagemRepository;

	@Autowired
	ModelMapper modelMapper;


	public List<Produto> getAllProdutos() {
		
		if(produtoRepository.count() == 0) {
			throw new CustomException("Não há produtos cadastrados");
		}
		
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
		Optional<Produto> produtoEncontrada = produtoRepository.findById(id);
		
		if(produtoEncontrada.isEmpty()) {
			throw new NoSuchElementException("Produto com id: " + id + " não encontrada!");
		}
		produtoRepository.deleteById(id);
		Produto produtoDeletada = produtoRepository.findById(id).orElse(null);
		return produtoDeletada == null;
	}

	// -------------
	// DTOs
	// -------------
	
	public ProdutoDTO getProdutoDTOById(Integer id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
		return modelMapper.map(produto, ProdutoDTO.class);
	}
	public ProdutoDTO getProdutoDTOByIdComFoto(Integer id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
		ProdutoDTO produtoDto = modelMapper.map(produto, ProdutoDTO.class);
		produtoDto.getImagem().setImagemDados(produto.getImagem().getDados());
		return produtoDto;
	}
	
	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
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
		
		ProdutoDTO produtoDto = new ProdutoDTO();
		
		return produtoDto;
	}
	
	public ProdutoDTO saveProdutoDTOComFoto(String produtoDTOString, MultipartFile file) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			produtoDTO = objMapper.readValue(produtoDTOString, ProdutoDTO.class);
		} catch (IOException e) {
			throw new UploadArquivoException("Ocorreu um erro ao transformar o produto em ProdutoDTO");
		}
		
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		produto.setQtdEstoque(produtoDTO.getQtdEstoque());
		produto.setDataCadastro(produtoDTO.getDataCadastro());
		
		Produto produtoExistente = produtoRepository.findByDescricao(produto.getDescricao());
		if (produtoExistente != null) {
			throw new ProdutoDescricaoDuplicadaException();
		}

		Categoria categoria = categoriaRepository.findById(produtoDTO.getIdCategoria())
							  .orElseThrow(() -> new NoSuchElementException("Categoria"));
		produto.setCategoria(categoria);
		
		if (produto.getQtdEstoque() == null) {
			produto.setQtdEstoque(0);
		}
		produto.setQtdEstoque(produto.getQtdEstoque() + 1);
		produto.setDataCadastro(Date.from(Instant.now()));
		
		try {
			Imagem imagem = new Imagem();
			imagem.setDados(file.getBytes());
			imagem.setNome(file.getName());
			imagem.setTipo(file.getContentType());
			imagemRepository.save(imagem);
			produto.setImagem(imagem);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		produtoRepository.save(produto);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/produtos/dto-comfoto/{id}/foto")
				.buildAndExpand(produto.getIdProduto())
				.toUri();
		produto.getImagem().setUrl(uri.toString());
		
		
		ProdutoDTO produtoDtoSalvo = new ProdutoDTO();
		produtoDtoSalvo.setNome(produto.getNome());
		produtoDtoSalvo.setDescricao(produto.getDescricao());
		produtoDtoSalvo.setValorUnitario(produto.getValorUnitario());
		produtoDtoSalvo.setQtdEstoque(produto.getQtdEstoque());
		produtoDtoSalvo.setDataCadastro(produto.getDataCadastro());
		
		ImagemDTO imagemDto = modelMapper.map(produto.getImagem(), ImagemDTO.class);
		produtoDtoSalvo.setImagem(imagemDto);
		
		CategoriaDTO categoriadto = modelMapper.map(produto.getCategoria(), CategoriaDTO.class);
		produtoDtoSalvo.setCategoria(categoriadto);
		
		produtoDtoSalvo.getImagem().setUrl(uri.toString());
		
		return produtoDtoSalvo;
	}
	
}
