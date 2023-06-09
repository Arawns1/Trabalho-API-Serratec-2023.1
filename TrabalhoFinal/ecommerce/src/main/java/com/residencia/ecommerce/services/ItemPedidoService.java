package com.residencia.ecommerce.services;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.entites.ItemPedido;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.exception.EstoqueNegativoException;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return itemPedidoDeletada == null;
	}

	// --------------- //
	// DTOs //
	// ----------------//

	public ItemPedidoDTO getItemPedidoDTOById(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Item Pedido", id));
		return modelMapper.map(itemPedido, ItemPedidoDTO.class);
	}

	public ItemPedidoDTO saveItemPedidoDTO(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();

		itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
		
		itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());

		Produto produto = produtoRepository.findById(itemPedidoDTO.getProdutoID())
				.orElseThrow(() -> new NoSuchElementException("Produto", itemPedidoDTO.getProdutoID()));
		
		itemPedido.setPrecoVenda(produto.getValorUnitario());
		itemPedido.setValorBruto(
				produto.getValorUnitario()
				.multiply(BigDecimal.valueOf(itemPedidoDTO.getQuantidade())));
		BigDecimal valorPorcentagem = itemPedido.getValorBruto()
				.multiply(BigDecimal.valueOf(itemPedidoDTO.getPercentualDesconto()));

		itemPedido.setValorLiquido((itemPedido.getValorBruto()).subtract(valorPorcentagem));

		itemPedido.setProduto(produto);
		
		if(itemPedido.getProduto().getQtdEstoque() < itemPedidoDTO.getQuantidade()) {
			throw new EstoqueNegativoException(1);
		}
		
		itemPedidoRepository.save(itemPedido);
		produto.setQtdEstoque(produto.getQtdEstoque() - itemPedido.getQuantidade());
		
		produtoRepository.save(produto);
		
		ItemPedidoDTO itemPedidoSalvo = modelMapper.map(itemPedido, ItemPedidoDTO.class);
		itemPedidoSalvo.setIdItemPedido(itemPedido.getIdItemPedido());

		return itemPedidoSalvo;
	}
}
