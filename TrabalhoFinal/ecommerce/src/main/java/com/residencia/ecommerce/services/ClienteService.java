package com.residencia.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.exception.ClienteCpfDuplicadoException;
import com.residencia.ecommerce.exception.ClienteEmailDuplicadoException;
import com.residencia.ecommerce.exception.ClienteNotFoundException;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.EnderecoRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	ModelMapper modelMapper;

	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
	}
	
	@Transactional
	public Optional<Cliente> findClienteByEmail(String email){
		return clienteRepository.findClienteByEmail(email);
	}

	public Cliente saveCliente(Cliente cliente) {
		// Verificações de duplicacao de cpf e email
		Cliente clienteCpfExistente = clienteRepository.findByCpf(cliente.getCpf());
		Cliente clienteEmailExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteCpfExistente != null) {
			throw new ClienteCpfDuplicadoException();
		} else if (clienteEmailExistente != null) {
			throw new ClienteEmailDuplicadoException();
		}
		// Fim da verificação
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Boolean deleteCliente(Integer id) {
		Optional<Cliente> clienteEncontrada = clienteRepository.findById(id);

		if (clienteEncontrada.isEmpty()) {
			throw new NoSuchElementException("Cliente com id: " + id + " não encontrada!");
		}
		clienteRepository.deleteById(id);
		Cliente clienteDeletada = clienteRepository.findById(id).orElse(null);
		return clienteDeletada == null;
	}

	// ---------------//
	// DTOs //
	// --------------//

	public ClienteDTO getClienteDTOById(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {

		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

		// Verificações de duplicacao de cpf e email
		Cliente clienteCpfExistente = clienteRepository.findByCpf(cliente.getCpf());
		Cliente clienteEmailExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteCpfExistente != null) {
			throw new ClienteCpfDuplicadoException();
		} else if (clienteEmailExistente != null) {
			throw new ClienteEmailDuplicadoException();
		}
		// Fim da verificação

		// Realiza a busca no ViaCep
		Endereco enderecoViaCep = enderecoService.buscaCep(clienteDTO);
		cliente.setEndereco(enderecoViaCep);
		cliente.setRoles(clienteDTO.getRole());

		Cliente clienteSalvo = clienteRepository.save(cliente);

		ClienteDTO clienteSalvoDTO = modelMapper.map(clienteSalvo, ClienteDTO.class);
		clienteSalvoDTO.setCep(clienteSalvo.getEndereco().getCep());
		clienteSalvoDTO.setComplemento(clienteSalvo.getEndereco().getComplemento());
		clienteSalvoDTO.setNumero(clienteSalvo.getEndereco().getNumero());
		clienteSalvoDTO.setRole(clienteSalvo.getRoles());
		return clienteSalvoDTO;
	}
}
