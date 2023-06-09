package com.trabalho.escola.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.escola.entities.Instrutor;
import com.trabalho.escola.exception.InstrutorNotFoundException;
import com.trabalho.escola.exception.NoRegisteredInstrutorException;
import com.trabalho.escola.repositories.InstrutorRepository;

import jakarta.mail.MessagingException;

@Service
public class InstrutorService {

	@Autowired
	InstrutorRepository instrutorRepository;

	@Autowired
	EmailService emailService;

	public List<Instrutor> getAllInstrutores() {
		if (instrutorRepository.count() == 0) {
			throw new NoRegisteredInstrutorException();
		} else {
			return instrutorRepository.findAll();
		}
	}

	public Instrutor getInstrutorById(Integer id) {
		return instrutorRepository.findById(id).orElseThrow(() -> new InstrutorNotFoundException(id));
	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		Instrutor instrutorSalvo = instrutorRepository.save(instrutor);
		try {
			emailService.enviarEmail("gabrieldamico22@gmail.com", "Instrutor cadastrado", instrutorSalvo);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return instrutorSalvo;
	}

	public Instrutor updateInstrutor(Instrutor instrutor, Integer id) {
		return instrutorRepository.save(instrutor);
	}

	public Boolean deleteInstrutor(Integer id) {
		instrutorRepository.deleteById(id);
		Instrutor resp = instrutorRepository.findById(id).orElseThrow(() -> new InstrutorNotFoundException(id));
		if (resp == null) {
			return true;
		} else {
			return false;
		}
	}
}
