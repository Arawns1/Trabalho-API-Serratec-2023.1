package com.residencia.ecommerce.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.DTO.CategoriaDTO;
import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	// ---------------//
	//	    DTOs	  //
	//----------------//
	
	
	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
		categoriaRepository.save(categoria);
		return modelMapper.map(categoria, CategoriaDTO.class);
	}
	
}
