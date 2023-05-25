package com.residencia.trabalho_final.controller;

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

import com.residencia.trabalho_final.entites.Pedido;
import com.residencia.trabalho_final.services.PedidoService;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getAllPedidos(){
		//return pedidoService.getAllPedidos();
		return new ResponseEntity<>(pedidoService.getAllPedidos(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id){
		//return pedidoService.getPedidoById(id);
		Pedido pedidoResponse = pedidoService.getPedidoById(id);
		if(pedidoResponse == null) {
			return new ResponseEntity<>(pedidoResponse, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(pedidoResponse,HttpStatus.FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Pedido> savePedido(@RequestBody Pedido pedido){
		return new ResponseEntity<>(pedidoService.savePedido(pedido),HttpStatus.CREATED);
	}
	
	@PutMapping
	//@PutMapping("/{id}")
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, Integer id){
		
		Pedido pedidoResponse = pedidoService.updatePedido(pedido,id);
		if(pedidoResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		}
		else {
			return new ResponseEntity<>(pedidoResponse,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePedido(@PathVariable Integer id) {
		
		Boolean response = pedidoService.deletePedido(id);
		
		if(response) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

}
