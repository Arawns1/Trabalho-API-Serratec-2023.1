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

import com.residencia.ecommerce.dto.Seguranca.MessageResponseDTO;
import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping

	public ResponseEntity<List<Categoria>> getAllCategorias() {
		return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
		Categoria categoriaResponse = categoriaService.getCategoriaById(id);
		if (categoriaResponse == null) {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria categoriaResponse = categoriaService.saveCategoria(categoria);
		if (categoriaResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.OK);
		}
	}
	
	
	@PutMapping
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, Integer id) {
		Categoria categoriaResponse = categoriaService.updateCategoria(categoria, id);
		if (categoriaResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
		Boolean response = categoriaService.deleteCategoria(id);
		if (response) {
			return ResponseEntity.ok(new MessageResponseDTO("Categoria deletada com Sucesso!"));
		} else {
			return ResponseEntity.badRequest()
								 .body(
								  new MessageResponseDTO("Não foi possível deletar a Categoria")
								  );
		}
	}
}
