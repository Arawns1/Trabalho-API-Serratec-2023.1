package com.residencia.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {
  @Autowired
  EnderecoRepository enderecoRepository;

  public List<Endereco> getAllEnderecos() {
    return enderecoRepository.findAll();
  }

  public Endereco getEnderecoById(Integer id) {
    return enderecoRepository.findById(id).orElse(null);
  }

  public Endereco saveEndereco(Endereco endereco) {
    return enderecoRepository.save(endereco);
  }

  public Endereco updateEndereco(Endereco endereco, Integer id) {
    return enderecoRepository.save(endereco);
  }

  public Boolean deleteEndereco(Integer id) {
		Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(id);
		
		if(enderecoEncontrado.isEmpty()) {
			throw new NoSuchElementException("Endereco com id: " + id + " não encontrado!");
		}
		enderecoRepository.deleteById(id);
		Endereco enderecoDeletado = enderecoRepository.findById(id).orElse(null);
		return enderecoDeletado == null;
	}
}
