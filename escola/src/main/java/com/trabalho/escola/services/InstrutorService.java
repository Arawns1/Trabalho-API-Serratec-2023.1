package com.trabalho.escola.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.escola.entities.Instrutor;
import com.trabalho.escola.repositories.InstrutorRepository;

@Service
public class InstrutorService {
	
	@Autowired
	InstrutorRepository instrutorRepository;
	
	public List<Instrutor> getAllInstrutores() {
		return instrutorRepository.findAll();
	}
	
	public Instrutor getInstrutorById(Integer id) {
		return instrutorRepository.findById(id).orElse(null);
	}
	
	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}
	
	public Instrutor updateInstrutor(Instrutor instrutor, Integer id) {
		return instrutorRepository.save(instrutor);
	}
	
	public Boolean deleteInstrutor(Integer id) {
		instrutorRepository.deleteById(id);
		Instrutor resp = instrutorRepository.findById(id).orElse(null);
		if(resp == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
