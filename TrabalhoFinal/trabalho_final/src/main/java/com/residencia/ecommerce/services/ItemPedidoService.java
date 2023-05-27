package com.residencia.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.DTO.ItemPedidoDTO;
import com.residencia.ecommerce.entites.ItemPedido;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ModelMapper modelMapper;

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
		if (itemPedidoDeletada == null) {
			return true;
		} else {
			return false;
		}

	}

	// ---------------//
	// 		DTOs 	   //
	// ----------------//

	public ItemPedidoDTO saveItemPedidoDTO(ItemPedidoDTO itemPedidoDTO) {
		
		ItemPedido itemPedido = new ItemPedido();
		
		itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
		itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
		
		Produto produto = produtoRepository.findById(itemPedidoDTO.getProdutoID())
						 .orElseThrow(() -> new NoSuchElementException("Produto", itemPedidoDTO.getProdutoID()));
		
		itemPedido.setPrecoVenda(produto.getValorUnitario());
		itemPedido.setValorBruto(produto.getValorUnitario()
								.multiply(BigDecimal.valueOf(itemPedidoDTO.getQuantidade())));
		BigDecimal valorPorcentagem = itemPedido.getValorBruto()
								 	  .multiply(BigDecimal.valueOf(itemPedidoDTO.getPercentualDesconto()));
		
		itemPedido.setValorLiquido((itemPedido.getValorBruto()).subtract(valorPorcentagem));
		itemPedido.setProduto(produto);
		itemPedidoRepository.save(itemPedido);
		return modelMapper.map(itemPedido, ItemPedidoDTO.class);
	}

}
