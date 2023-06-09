package com.residencia.ecommerce.controller.seguranca;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.Seguranca.JwtResponseDTO;
import com.residencia.ecommerce.dto.Seguranca.LoginRequestDTO;
import com.residencia.ecommerce.dto.Seguranca.MessageResponseDTO;
import com.residencia.ecommerce.dto.Seguranca.SignupRequestDTO;
import com.residencia.ecommerce.entites.seguranca.Role;
import com.residencia.ecommerce.entites.seguranca.RoleEnum;
import com.residencia.ecommerce.entites.seguranca.User;
import com.residencia.ecommerce.repositories.seguranca.RoleRepository;
import com.residencia.ecommerce.repositories.seguranca.UserRepository;
import com.residencia.ecommerce.security.jwt.JwtUtils;
import com.residencia.ecommerce.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Username já utilizado!"));
		}

		User user = new User(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role clienteRole = roleRepository.findByName(RoleEnum.ROLE_CLIENTE)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(clienteRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);

					break;
				case "cliente":
					Role clienteRole = roleRepository.findByName(RoleEnum.ROLE_CLIENTE)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(clienteRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponseDTO("Usuário registrado com sucesso!"));
	}
}
