package com.trabalho.escola.controllers;

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
import com.trabalho.escola.entities.Telefone;
import com.trabalho.escola.services.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	TelefoneService telefoneService;
	
	@GetMapping
	public ResponseEntity<List<Telefone>> getAllTelefonees() {
		return new ResponseEntity<>(telefoneService.getAllTelefones(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> getTelefoneById(@PathVariable Integer id) {
		Telefone response = telefoneService.getTelefoneById(id);
		if(response != null) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Telefone> saveTelefone(@RequestBody Telefone telefone) {
		return new ResponseEntity<>(telefoneService.saveTelefone(telefone),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Telefone> updateTelefone(@RequestBody Telefone telefone, Integer id) {
		return new ResponseEntity<>(telefoneService.updateTelefone(telefone, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTelefone(@PathVariable Integer id) {
		Boolean response = telefoneService.deleteTelefone(id);
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
