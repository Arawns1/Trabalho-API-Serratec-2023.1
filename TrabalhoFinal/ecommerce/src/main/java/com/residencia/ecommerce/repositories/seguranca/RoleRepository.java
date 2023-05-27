package com.residencia.ecommerce.repositories.seguranca;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residencia.ecommerce.entites.seguranca.Role;
import com.residencia.ecommerce.entites.seguranca.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}