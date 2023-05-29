package com.residencia.ecommerce.controller;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entites.Pedido;
import com.residencia.ecommerce.services.PedidoService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	@Operation( summary  = "Lista todos os pedidos")
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		return new ResponseEntity<>(pedidoService.getAllPedidos(), HttpStatus.FOUND);
	}

	@PutMapping
	@Operation( summary  = "Modifica todos os pedidos")
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, Integer id) {
		Pedido pedidoResponse = pedidoService.updatePedido(pedido, id);
		if (pedidoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	@Operation( summary  = "Deleta um pedido específico")
	public ResponseEntity<Boolean> deletePedido(@PathVariable Integer id) {
		Boolean response = pedidoService.deletePedido(id);

		if (response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// --------//
	// DTOs //
	// ------ //
	
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<List<PedidoDTO>> getPedidoDTOByIdCliente(@PathVariable Integer idCliente) {
		List<PedidoDTO> pedidoResponse = pedidoService.getAllPedidosDTOByIdCliente(idCliente);
		if (pedidoResponse == null) {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("{idPedido}/cliente/{idCliente}")
	public ResponseEntity<PedidoDTO> getPedidoDTOByIdCliente(@PathVariable Integer idCliente,
																		@PathVariable Integer idPedido) {
		PedidoDTO pedidoResponse = pedidoService.getPedidoDTOByIdCliente(idCliente, idPedido);
		if (pedidoResponse == null) {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.FOUND);
		}
	}
	
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<PedidoDTO> getPedidoDTOById(@PathVariable Integer id) {
		PedidoDTO pedidoResponse = pedidoService.getPedidoDTOById(id);
		if (pedidoResponse == null) {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.FOUND);
		}
	}

	@PostMapping("/dto")
	public ResponseEntity<PedidoDTO> savePedidoDTO(@Valid @RequestBody PedidoDTO pedidoDTO) {
		return new ResponseEntity<>(pedidoService.savePedidoDTO(pedidoDTO), HttpStatus.CREATED);
	}
}
