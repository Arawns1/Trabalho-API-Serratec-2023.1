package com.residencia.trabalho_final.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.trabalho_final.DTO.ClienteDTO;
import com.residencia.trabalho_final.DTO.ViaCepDTO;
import com.residencia.trabalho_final.entites.Cliente;
import com.residencia.trabalho_final.entites.Endereco;
import com.residencia.trabalho_final.exception.ClienteCpfDuplicadoException;
import com.residencia.trabalho_final.exception.ClienteEmailDuplicadoException;
import com.residencia.trabalho_final.exception.ClienteNotFoundException;
import com.residencia.trabalho_final.repositories.ClienteRepository;
import com.residencia.trabalho_final.repositories.EnderecoRepository;

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
		System.out.println("aa");
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		System.out.println(cliente);
		RestTemplate restTemplate = new RestTemplate();
		String uri= "http://viacep.com.br/ws/{cep}/json";
		Map<String, String> params = new HashMap<>();	
		params.put("cep", clienteDTO.getCep());
		ViaCepDTO responseViaCep = restTemplate.getForObject(uri, ViaCepDTO.class, params);
		
		System.out.println("aa");
		Endereco endereco = new Endereco();
		endereco.setCep(responseViaCep.getCep());
		endereco.setRua(responseViaCep.getLogradouro());
		endereco.setBairro(responseViaCep.getBairro());
		endereco.setCidade(responseViaCep.getLocalidade());
		endereco.setUf(responseViaCep.getUf());
		endereco.setComplemento(clienteDTO.getComplemento());
		endereco.setNumero(clienteDTO.getNumero());
		
		System.out.println(endereco);
		cliente.setEndereco(endereco);
		
		enderecoRepository.save(endereco);
		
		clienteRepository.save(cliente);
		
		return modelMapper.map(cliente, ClienteDTO.class);
	}
}
