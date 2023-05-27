package com.residencia.ecommerce.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.ecommerce.DTO.ClienteDTO;
import com.residencia.ecommerce.DTO.ViaCepDTO;
import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.exception.ClienteCpfDuplicadoException;
import com.residencia.ecommerce.exception.ClienteEmailDuplicadoException;
import com.residencia.ecommerce.exception.ClienteNotFoundException;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.EnderecoRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
	}

	public Cliente saveCliente(Cliente cliente) {
		Cliente clienteCpfExistente = clienteRepository.findByCpf(cliente.getCpf());
		Cliente clienteEmailExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteCpfExistente != null) {
			throw new ClienteCpfDuplicadoException();
		} else if (clienteEmailExistente != null) {
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
		if (clienteDeletada == null) {
			return true;
		} else {
			return false;
		}

	}

	// ---------------//
	//		 DTOs 	 //
	// --------------//

	public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		
		Cliente clienteCpfExistente = clienteRepository.findByCpf(cliente.getCpf());
		Cliente clienteEmailExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteCpfExistente != null) {
			throw new ClienteCpfDuplicadoException();
		} else if (clienteEmailExistente != null) {
			throw new ClienteEmailDuplicadoException();
		}
		
		RestTemplate restTemplate = new RestTemplate();
		String uri= "http://viacep.com.br/ws/{cep}/json";
		Map<String, String> params = new HashMap<>();	
		params.put("cep", clienteDTO.getCep());
		ViaCepDTO responseViaCep = restTemplate.getForObject(uri, ViaCepDTO.class, params);
		
		Endereco endereco = new Endereco();
		endereco.setCep(responseViaCep.getCep());
		endereco.setRua(responseViaCep.getLogradouro());
		endereco.setBairro(responseViaCep.getBairro());
		endereco.setCidade(responseViaCep.getLocalidade());
		endereco.setUf(responseViaCep.getUf());
		endereco.setComplemento(clienteDTO.getComplemento());
		endereco.setNumero(clienteDTO.getNumero());
		
		cliente.setEndereco(endereco);
		
		enderecoRepository.save(endereco);
		
		clienteRepository.save(cliente);
		
		return modelMapper.map(cliente, ClienteDTO.class);
	}
}