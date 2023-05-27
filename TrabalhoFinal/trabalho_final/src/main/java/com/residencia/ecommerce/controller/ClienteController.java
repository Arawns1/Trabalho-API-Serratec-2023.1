package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.DTO.ClienteDTO;
import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		// return clienteService.getAllClientes();
		return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		// return clienteService.getClienteById(id);
		Cliente clienteResponse = clienteService.getClienteById(id);
		if (clienteResponse == null) {
			return new ResponseEntity<>(clienteResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(clienteResponse, HttpStatus.FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, Integer id) {

		Cliente clienteResponse = clienteService.updateCliente(cliente, id);
		if (clienteResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCliente(@PathVariable Integer id) {

		Boolean response = clienteService.deleteCliente(id);

		if (response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// -------  //
	//   DTOs 	//
	// ------  //
	/* --- INPUT --- */
	@PostMapping("/dto")
	public ResponseEntity<ClienteDTO> saveCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		return new ResponseEntity<>(clienteService.saveClienteDTO(clienteDTO), HttpStatus.CREATED);
	}

}
