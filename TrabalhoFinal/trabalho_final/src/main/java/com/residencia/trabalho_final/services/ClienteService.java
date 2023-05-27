package com.residencia.trabalho_final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.trabalho_final.entites.Cliente;
import com.residencia.trabalho_final.exception.ClienteCpfDuplicadoException;
import com.residencia.trabalho_final.exception.ClienteEmailDuplicadoException;
import com.residencia.trabalho_final.exception.ClienteNotFoundException;
import com.residencia.trabalho_final.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;	
	
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}
	
	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElseThrow (()-> new ClienteNotFoundException (id));
	}
	
	public Cliente saveCliente(Cliente cliente) {
		Cliente clienteCpfExistente = clienteRepository.findByCpf(cliente.getCpf());
		Cliente clienteEmailExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteCpfExistente != null) {
			throw new ClienteCpfDuplicadoException();
		}else if(clienteEmailExistente != null){
			throw new ClienteEmailDuplicadoException();
		}
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(Cliente cliente, Integer id) {
		return clienteRepository.save(cliente);
	}
	
	public Boolean deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
		Cliente clienteDeletada = clienteRepository.findById(id).orElse(null);
		if(clienteDeletada == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
