package com.residencia.ecommerce.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.dto.ViaCepDTO;
import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.exception.CEPNotFoundException;
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
  
  public Endereco buscaCep(ClienteDTO clienteDTO) {
	Endereco endereco = new Endereco();
	  try {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json";
		Map<String, String> params = new HashMap<>();
		params.put("cep", clienteDTO.getCep());
		
		
			ViaCepDTO responseViaCep = restTemplate.getForObject(uri, ViaCepDTO.class, params);
			if (responseViaCep == null || responseViaCep.getErro()) {
				throw new CEPNotFoundException(clienteDTO.getCep());
			}
			endereco.setCep(responseViaCep.getCep());
			endereco.setRua(responseViaCep.getLogradouro());
			endereco.setBairro(responseViaCep.getBairro());
			endereco.setCidade(responseViaCep.getLocalidade());
			endereco.setUf(responseViaCep.getUf());
			endereco.setComplemento(clienteDTO.getComplemento());
			endereco.setNumero(clienteDTO.getNumero());
			enderecoRepository.save(endereco);
			
		} catch (HttpClientErrorException | HttpServerErrorException | UnknownHttpStatusCodeException e) {
			throw new CEPNotFoundException(clienteDTO.getCep());
		}
		
		return endereco;
  }
  
  
}
