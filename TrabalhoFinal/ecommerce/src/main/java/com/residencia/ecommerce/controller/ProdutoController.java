package com.residencia.ecommerce.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.ecommerce.dto.ProdutoDTO;
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
	public ResponseEntity<Boolean> deleteProduto(@PathVariable Integer id) {
		Boolean response = produtoService.deleteProduto(id);

		if (response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
			 HttpHeaders headers = new HttpHeaders();
			 headers.set("Content-type", MediaType.IMAGE_JPEG_VALUE);
			   // headers.set("Content-type", MediaType.IMAGE_JPEG_VALUE);
			    //headers.set("Content-Disposition","attachment; filename=\""+ produtoResponse.getNomeImagem()); // to view in browser change attachment to inline 
			    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(produtoResponse);
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
