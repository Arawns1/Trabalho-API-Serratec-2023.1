package com.residencia.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entites.Pedido;
import com.residencia.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;	
	
	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido updatePedido(Pedido pedido, Integer id) {
		return pedidoRepository.save(pedido);
	}
	
	public Boolean deletePedido(Integer id) {
		pedidoRepository.deleteById(id);
		Pedido pedidoDeletada = pedidoRepository.findById(id).orElse(null);
		if(pedidoDeletada == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
