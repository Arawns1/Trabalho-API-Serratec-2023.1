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

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.services.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	@Operation( summary  = "Lista todas as categorias")
	public ResponseEntity<List<Categoria>> getAllCategorias() {
		return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.FOUND);
	}

	@PutMapping
	@Operation( summary  = "Modifica todas as categorias")
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, Integer id) {
		Categoria categoriaResponse = categoriaService.updateCategoria(categoria, id);
		if (categoriaResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	@Operation( summary  = "Deleta uma categoria especifica")
	public ResponseEntity<Boolean> deleteCategoria(@PathVariable Integer id) {
		Boolean response = categoriaService.deleteCategoria(id);

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
	public ResponseEntity<CategoriaDTO> getCategoriaDTOById(@PathVariable Integer id) {
		CategoriaDTO categoriaResponse = categoriaService.getCategoriaDTOById(id);
		if (categoriaResponse == null) {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.FOUND);
		}
	}

	@PostMapping("/dto")
	public ResponseEntity<CategoriaDTO> saveCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		return new ResponseEntity<>(categoriaService.saveCategoriaDTO(categoriaDTO), HttpStatus.CREATED);
	}
}
