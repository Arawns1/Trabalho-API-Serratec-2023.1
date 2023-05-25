package com.residencia.trabalho_final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.trabalho_final.entites.Categoria;
import com.residencia.trabalho_final.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;	
	
	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}
	
	public Categoria getCategoriaById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria updateCategoria(Categoria categoria, Integer id) {
		return categoriaRepository.save(categoria);
	}
	
	public Boolean deleteCategoria(Integer id) {
		categoriaRepository.deleteById(id);
		Categoria categoriaDeletada = categoriaRepository.findById(id).orElse(null);
		if(categoriaDeletada == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
