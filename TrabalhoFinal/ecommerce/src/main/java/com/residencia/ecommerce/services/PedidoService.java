package com.residencia.ecommerce.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.entites.ItemPedido;
import com.residencia.ecommerce.entites.Pedido;
import com.residencia.ecommerce.exception.ClienteNotFoundException;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {
  @Autowired
  PedidoRepository pedidoRepository;

  @Autowired
  ClienteRepository clienteRepository;
  
  @Autowired
  ItemPedidoRepository itemPedidoRepository;

  @Autowired
  ModelMapper modelMapper;

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
    return pedidoDeletada == null;
  }

  // --------------- //
  // DTOs //
  // ----------------//

  public PedidoDTO getPedidoDTOById(Integer id) {
    Pedido pedido = pedidoRepository
      .findById(id)
      .orElseThrow(() -> new NoSuchElementException("Pedido", id));
    return modelMapper.map(pedido, PedidoDTO.class);
  }

  
  /**
   * TODO NÃO ESTÁ SALVAO OS VALORES VINDO DO ItemPedido
  */
  public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) {
    Pedido pedido = new Pedido();

    Cliente cliente = clienteRepository
      .findById(pedidoDTO.getIdCliente())
      .orElseThrow(() -> new ClienteNotFoundException(pedidoDTO.getIdCliente()));

    pedido.setCliente(cliente);
    pedido.setStatus("Pago");
    List<ItemPedido> items = new ArrayList<>();
    BigDecimal valorTotal = new BigDecimal("0.00");
    
    for(Integer itemPedidoId : pedidoDTO.getIdsItemPedido()) {
    	ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() -> new NoSuchElementException("Item Pedido", itemPedidoId)); 
    	itemPedido.setPedido(pedido);
    	valorTotal = valorTotal.add(itemPedido.getValorLiquido());
    	items.add(itemPedido);
    	
    }
    
    pedido.setItems(items);
    pedido.setValorTotal(valorTotal);
    pedido.setDataPedido(Date.from(Instant.now()));
    
    pedidoRepository.save(pedido);
    itemPedidoRepository.saveAll(items);
    
    List<ItemPedidoDTO> itemsDTO = new ArrayList<>();
    for(ItemPedido itemPedido : items) {
    	ItemPedidoDTO itemPedidoDto = modelMapper.map(itemPedido, ItemPedidoDTO.class);
    	itemsDTO.add(itemPedidoDto);
    }
   
    PedidoDTO pedidoSalvo = modelMapper.map(pedido, PedidoDTO.class);
    pedidoSalvo.setItensPedido(itemsDTO);
	pedidoSalvo.setIdPedido(pedido.getIdPedido());
	pedidoSalvo.setCliente(modelMapper.map(cliente, ClienteDTO.class));
	pedidoSalvo.setValorTotal(valorTotal);
    
    return pedidoSalvo;
  }
}
