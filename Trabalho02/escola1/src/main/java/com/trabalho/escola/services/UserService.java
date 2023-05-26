package com.trabalho.escola.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.escola.entities.seguranca.User;
import com.trabalho.escola.exception.InstrutorNotFoundException;
import com.trabalho.escola.exception.NoRegisteredInstrutorException;
import com.trabalho.escola.repositories.seguranca.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		if (userRepository.count() == 0) {
			throw new NoRegisteredInstrutorException();
		} else {
			return userRepository.findAll();
		}
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new InstrutorNotFoundException(id));
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException());
	}
}
