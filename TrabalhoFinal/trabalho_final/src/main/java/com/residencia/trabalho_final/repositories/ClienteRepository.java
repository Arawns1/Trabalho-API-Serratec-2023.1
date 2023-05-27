package com.residencia.trabalho_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.trabalho_final.entites.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	Cliente findByCpf (String cpf);
	Cliente findByEmail (String email);

}
