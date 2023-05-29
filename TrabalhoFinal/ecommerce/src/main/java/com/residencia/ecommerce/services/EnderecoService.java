package com.residencia.ecommerce.services;

import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.repositories.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    enderecoRepository.deleteById(id);
    Endereco enderecoDeletada = enderecoRepository.findById(id).orElse(null);
    return enderecoDeletada == null;
  }
}
