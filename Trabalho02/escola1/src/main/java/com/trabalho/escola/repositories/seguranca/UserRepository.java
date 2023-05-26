package com.trabalho.escola.repositories.seguranca;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.escola.entities.seguranca.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
	
	Optional<User> findByPassword(String password);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}