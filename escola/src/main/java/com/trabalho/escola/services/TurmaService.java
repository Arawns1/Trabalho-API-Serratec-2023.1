package com.trabalho.escola.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.escola.entities.Telefone;
import com.trabalho.escola.entities.Turma;
import com.trabalho.escola.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}
	public Turma getTurmaById(Integer id) {
		return turmaRepository.findById(id).orElse(null);
	}
	public Turma findById(Integer Id) {
		return turmaRepository.findById(Id).orElse(null);
	}
	public Turma saveTurma (Turma turma) {
		return turmaRepository.save(turma);
	}
	public Turma UpdateEditora (Turma turma ) {
		return turmaRepository.save(turma);
	}
	public Boolean delTurma(Integer Id) {
    turmaRepository.deleteById(Id);
		Turma resp = turmaRepository.findById(Id).orElse(null);
		if(resp == null) {
			return true;
		}
		else {
			return false;
		}
	}
}

