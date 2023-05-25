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

import com.residencia.trabalho_final.entites.Categoria;
import com.residencia.trabalho_final.services.CategoriaService;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategorias(){
		//return categoriaService.getAllCategorias();
		return new ResponseEntity<>(categoriaService.getAllCategorias(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id){
		//return categoriaService.getCategoriaById(id);
		Categoria categoriaResponse = categoriaService.getCategoriaById(id);
		if(categoriaResponse == null) {
			return new ResponseEntity<>(categoriaResponse, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(categoriaResponse,HttpStatus.FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria){
		return new ResponseEntity<>(categoriaService.saveCategoria(categoria),HttpStatus.CREATED);
	}
	
	@PutMapping
	//@PutMapping("/{id}")
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, Integer id){
		
		Categoria categoriaResponse = categoriaService.updateCategoria(categoria,id);
		if(categoriaResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else {
			return new ResponseEntity<>(categoriaResponse,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCategoria(@PathVariable Integer id) {
		
		Boolean response = categoriaService.deleteCategoria(id);
		
		if(response) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

}
