package com.residencia.trabalho_final.controller;

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

import com.residencia.trabalho_final.entites.Produto;
import com.residencia.trabalho_final.services.ProdutoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos(){
		//return produtoService.getAllProdutos();
		return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id){
		//return produtoService.getProdutoById(id);
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
	//@PutMapping("/{id}")
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

}