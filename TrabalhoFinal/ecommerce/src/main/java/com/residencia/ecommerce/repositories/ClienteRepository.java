package com.residencia.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.entites.seguranca.User;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	Cliente findByCpf (String cpf);
	Cliente findByEmail (String email);
	
	Optional<Cliente> findByUsername(String username);
	Boolean existsByUsername(String username);

}



