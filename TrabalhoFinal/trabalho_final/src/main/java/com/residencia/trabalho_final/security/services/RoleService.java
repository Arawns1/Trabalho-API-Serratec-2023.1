package com.residencia.trabalho_final.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.residencia.trabalho_final.entites.seguranca.Role;
import com.residencia.trabalho_final.repositories.seguranca.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
