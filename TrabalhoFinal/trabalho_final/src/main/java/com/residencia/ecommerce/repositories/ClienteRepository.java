package com.residencia.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entites.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	Cliente findByCpf (String cpf);
	Cliente findByEmail (String email);
}

