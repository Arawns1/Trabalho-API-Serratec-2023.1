package com.residencia.ecommerce.services;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.CategoriaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return categoriaDeletada == null;
	}

	// ---------------//
	// DTOs //
	// ----------------//

	public CategoriaDTO getCategoriaDTOById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Categoria", id));
		return modelMapper.map(categoria, CategoriaDTO.class);
	}

	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
		categoriaRepository.save(categoria);
		CategoriaDTO categoriaSalva = modelMapper.map(categoria, CategoriaDTO.class);
		categoriaSalva.setIdCategoria(categoria.getIdCategoria());
		return categoriaSalva;
	}
}
