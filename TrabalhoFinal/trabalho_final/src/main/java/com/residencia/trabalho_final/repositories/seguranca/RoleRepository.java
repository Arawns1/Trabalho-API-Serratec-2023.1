package com.residencia.trabalho_final.repositories.seguranca;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residencia.trabalho_final.entites.seguranca.Role;
import com.residencia.trabalho_final.entites.seguranca.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}