package com.residencia.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.CategoriaRepository;

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
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		
		if(categoriaEncontrada.isEmpty()) {
			throw new NoSuchElementException("Categoria com id: " + id + " não encontrada!");
		}
		categoriaRepository.deleteById(id);
		Categoria categoriaDeletada = categoriaRepository.findById(id).orElse(null);
		return categoriaDeletada == null;
	}
}
