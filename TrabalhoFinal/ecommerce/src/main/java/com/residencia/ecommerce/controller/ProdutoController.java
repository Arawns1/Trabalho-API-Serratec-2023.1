package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Produto>> getAllProdutos(){
		return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id){
		Produto produtoResponse = produtoService.getProdutoById(id);
		if(produtoResponse == null) {
			return new ResponseEntity<>(produtoResponse, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(produtoResponse,HttpStatus.FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@Valid @RequestBody Produto produto){
		return new ResponseEntity<>(produtoService.saveProduto(produto),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, Integer id){
		
		Produto produtoResponse = produtoService.updateProduto(produto,id);
		if(produtoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else {
			return new ResponseEntity<>(produtoResponse,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProduto(@PathVariable Integer id) {
		
		Boolean response = produtoService.deleteProduto(id);
		
		if(response) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	//--------//
	//  DTOs  //
	// ------ //
	
	/*  ---  INPUT --- */
	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveCategoria(@Valid @RequestBody ProdutoDTO produtoDTO){
		return new ResponseEntity<>(produtoService.saveProdutoDTO(produtoDTO),HttpStatus.CREATED);
	}

}
