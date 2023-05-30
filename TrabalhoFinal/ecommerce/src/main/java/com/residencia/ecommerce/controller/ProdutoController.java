package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.dto.Seguranca.MessageResponseDTO;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return new ResponseEntity<>(produtoService.getAllProdutos(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
		Produto produtoResponse = produtoService.saveProduto(produto);
		if (produtoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
		}
	}

	@PutMapping
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, Integer id) {
		Produto produtoResponse = produtoService.updateProduto(produto, id);
		if (produtoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Integer id) {
		Boolean response = produtoService.deleteProduto(id);
		if (response) {
			return ResponseEntity.ok(new MessageResponseDTO("Produto deletado com Sucesso!"));
		} else {
			return ResponseEntity.badRequest()
								 .body(
								  new MessageResponseDTO("Não foi possível deletar o Produto")
								  );
		}
	}

	// --------//
	// DTOs //
	// ------ //

	@GetMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> getProdutoDTOById(@PathVariable Integer id) {
		ProdutoDTO produtoResponse = produtoService.getProdutoDTOById(id);
		if (produtoResponse == null) {
			return new ResponseEntity<>(produtoResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(produtoResponse, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/dto-comfoto/{id}")
	public ResponseEntity<?> getProdutoDTOByIdComFoto(@PathVariable Integer id) {
		ProdutoDTO produtoResponse = produtoService.getProdutoDTOByIdComFoto(id);
		if (produtoResponse == null) {
			return new ResponseEntity<>(produtoResponse, HttpStatus.NOT_FOUND);
		} else {
			 return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/dto-comfoto/{id}/foto")
	public ResponseEntity<?> getFotoByProdutoId(@PathVariable Integer id) {
		ProdutoDTO produtoResponse = produtoService.getProdutoDTOByIdComFoto(id);
		if (produtoResponse == null) {
			return new ResponseEntity<>(produtoResponse, HttpStatus.NOT_FOUND);
		} else {
			 HttpHeaders headers = new HttpHeaders();
			   headers.set("Content-type", produtoResponse.getImagem().getTipo());
			   headers.set("Content-Disposition","inline; filename=\""+ produtoResponse.getImagem().getNome()); 
			   headers.set("Content-length", String.valueOf(produtoResponse.getImagem().getImagemDados().length));
			 return new ResponseEntity<>(produtoResponse.getImagem().getImagemDados(), headers, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody ProdutoDTO produtoDTO){
		return new ResponseEntity<>(produtoService.saveProdutoDTO(produtoDTO), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/dto-comfoto", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ProdutoDTO> saveProdutoComImagem(@Valid @RequestPart("produto") String produtoDTO, 
																  @RequestPart("file") MultipartFile file) throws Exception {
		return new ResponseEntity<>(produtoService.saveProdutoDTOComFoto(produtoDTO, file), HttpStatus.CREATED);
	}
	
}
