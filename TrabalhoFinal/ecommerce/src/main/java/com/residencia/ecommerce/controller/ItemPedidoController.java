package com.residencia.ecommerce.controller;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.entites.ItemPedido;
import com.residencia.ecommerce.services.ItemPedidoService;
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
@RequestMapping("/itempedidos")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> getAllItemPedidos() {
		return new ResponseEntity<>(itemPedidoService.getAllItemPedidos(), HttpStatus.FOUND);
	}

	@PutMapping
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody ItemPedido itemPedido, Integer id) {
		ItemPedido itemPedidoResponse = itemPedidoService.updateItemPedido(itemPedido, id);
		if (itemPedidoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(itemPedidoResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteItemPedido(@PathVariable Integer id) {
		Boolean response = itemPedidoService.deleteItemPedido(id);

		if (response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// --------//
	// DTOs //
	// ------ //

	@GetMapping("/dto/{id}")
	public ResponseEntity<ItemPedidoDTO> getItemPedidoDTOById(@PathVariable Integer id) {
		ItemPedidoDTO itemPedidoResponse = itemPedidoService.getItemPedidoDTOById(id);
		if (itemPedidoResponse == null) {
			return new ResponseEntity<>(itemPedidoResponse, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(itemPedidoResponse, HttpStatus.FOUND);
		}
	}

	@PostMapping("/dto")
	public ResponseEntity<ItemPedidoDTO> saveItemPedidoDTO(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedidoDTO(itemPedidoDTO), HttpStatus.CREATED);
	}
}
