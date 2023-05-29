package com.residencia.ecommerce.controller;

import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.services.EnderecoService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	@Operation( summary  = "Lista todos os endereços")
	public ResponseEntity<List<Endereco>> getAllEnderecos() {
		return new ResponseEntity<>(enderecoService.getAllEnderecos(), HttpStatus.FOUND);
	}

	@GetMapping("/{id}")
	@Operation( summary  = "Retorna um endereço específico")
	public ResponseEntity<Endereco> getEnderecoById(@PathVariable Integer id) {
		Endereco enderecoResponse = enderecoService.getEnderecoById(id);
		if (enderecoResponse == null) {
			return new ResponseEntity<>(enderecoResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(enderecoResponse, HttpStatus.FOUND);
		}
	}

	@PostMapping
	@Operation( summary  = "Cria um novo endereço")
	public ResponseEntity<Endereco> saveEndereco(@Valid @RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation( summary  = "Modifica todos os endereços")
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco, Integer id) {
		Endereco enderecoResponse = enderecoService.updateEndereco(endereco, id);
		if (enderecoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	@Operation( summary  = "Deleta um endereço específico")
	public ResponseEntity<Boolean> deleteEndereco(@PathVariable Integer id) {
		Boolean response = enderecoService.deleteEndereco(id);

		if (response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
