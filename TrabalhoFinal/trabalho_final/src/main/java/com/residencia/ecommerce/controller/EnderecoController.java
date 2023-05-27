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

import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.services.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> getAllEnderecos(){
		return new ResponseEntity<>(enderecoService.getAllEnderecos(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getEnderecoById(@PathVariable Integer id){
		Endereco enderecoResponse = enderecoService.getEnderecoById(id);
		if(enderecoResponse == null) {
			return new ResponseEntity<>(enderecoResponse, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(enderecoResponse,HttpStatus.FOUND);
		}
	}
	
	@PostMapping

	public ResponseEntity<Endereco> saveEndereco(@Valid @RequestBody Endereco endereco){
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco, Integer id){
		
		Endereco enderecoResponse = enderecoService.updateEndereco(endereco,id);
		if(enderecoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else {
			return new ResponseEntity<>(enderecoResponse,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteEndereco(@PathVariable Integer id) {
		
		Boolean response = enderecoService.deleteEndereco(id);
		
		if(response) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

}
