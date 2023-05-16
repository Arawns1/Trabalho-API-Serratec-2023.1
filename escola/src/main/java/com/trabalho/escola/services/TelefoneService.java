package com.trabalho.escola.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.escola.entities.Telefone;
import com.trabalho.escola.repositories.TelefoneRepository;

@Service
public class TelefoneService {
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	public List<Telefone> findAll() {
		return telefoneRepository.findAll();
	}
	
	public Telefone findById(Integer id) {
		return telefoneRepository.findById(id).orElse(null);
	}
	
	public Telefone saveTelefone(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}
	
	public Telefone updateTelefone(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}
	
	public Boolean deleteTelefone(Integer id) {
		telefoneRepository.deleteById(id);
		Telefone resp = telefoneRepository.findById(id).orElse(null);
		if(resp == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
