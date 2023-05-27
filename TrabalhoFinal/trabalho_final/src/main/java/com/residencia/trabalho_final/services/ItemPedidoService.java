package com.residencia.trabalho_final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.trabalho_final.entites.ItemPedido;
import com.residencia.trabalho_final.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;	
	
	public List<ItemPedido> getAllItemPedidos() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido getItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido updateItemPedido(ItemPedido itemPedido, Integer id) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public Boolean deleteItemPedido(Integer id) {
		itemPedidoRepository.deleteById(id);
		ItemPedido itemPedidoDeletada = itemPedidoRepository.findById(id).orElse(null);
		if(itemPedidoDeletada == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
