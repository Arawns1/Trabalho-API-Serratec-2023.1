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
import com.residencia.ecommerce.dto.RelatorioDTO;
import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.entites.ItemPedido;
import com.residencia.ecommerce.entites.Pedido;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.exception.ClienteNotFoundException;
import com.residencia.ecommerce.exception.EstoqueNegativoException;
import com.residencia.ecommerce.exception.NoSuchElementException;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.PedidoRepository;

import jakarta.mail.MessagingException;

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
	
	@Autowired
	MailService mailService;
	

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
	
	private void enviarRelatorio (PedidoDTO pedidoDTO) {
		RelatorioDTO relatorioDTO = new RelatorioDTO (pedidoDTO.getItensPedido(),pedidoDTO);
		relatorioDTO.toString();
		String htmlItemsPedido = "";
		for (ItemPedidoDTO itemPedidoDTO:pedidoDTO.getItensPedido()){
			Integer quantidade = itemPedidoDTO.getQuantidade();
			Integer id = itemPedidoDTO.getProdutoID();
			BigDecimal precoVenda = itemPedidoDTO.getPrecoVenda();
			Double percentualDesconto = itemPedidoDTO.getPercentualDesconto();
			BigDecimal valorLiquido = itemPedidoDTO.getValorLiquido();
			String nome = itemPedidoDTO.getProduto().getNome();
			htmlItemsPedido += "<tr>";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+id+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+nome+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+precoVenda+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+quantidade+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+percentualDesconto+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+valorLiquido+"</td>\r\n";
			htmlItemsPedido += "</tr>";
			};
		String html = "<html>\r\n"
				+ "    <body>\r\n"
				+ "       <h2 style='color:green; text-align:center'> Pedido cadastrado! </h2>\r\n"
				+ "        <hr/>\r\n"
				+ "        <br/>\r\n"
				+ "        <br/>\r\n"
				+ "        <table style='margin-left: auto; margin-right:auto; border-collapse:collapse; border:1px solid #000000; background-color:#bfcac6'>\r\n"
				+ "			<tr>"
				+ "		    	<th>ID</th>"
				+ "			    <th>DATA</th>"
				+ "			    <th>VALOR TOTAL</th>"
				+ "			 </tr>"
				+ "            <tr>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+pedidoDTO.getIdPedido()+"</td>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+pedidoDTO.getDataPedido()+"</td>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+pedidoDTO.getValorTotal()+"</td>\r\n"
				+ "            </tr>\r\n"
				+ "        </table>\r\n"
				+ "        <table style='margin-left: auto; margin-right:auto; border-collapse:collapse; border:1px solid #000000; background-color:#bfcac6'>\r\n"
				+ "			<tr>"
				+ "		    	<th>CODIGO</th>"
				+ "			    <th>NOME</th>"
				+ "			    <th>PREÇO</th>"
				+ "			    <th>QUANTIDADE</th>"
				+ "			    <th>DESCONTO</th>"
				+ "			    <th>VALOR LIQUIDO</th>"
				+ "			 </tr>"
				+ htmlItemsPedido
				+ "        </table>\r\n"
				+ "    </body>\r\n"
				+ "</html>";		
		try {
			mailService.enviarEmail(pedidoDTO.getCliente().getEmail(),
					"Pedido cadastrado", 
					html);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------
	// DTOs 
	// ----------

	public List<PedidoDTO> getAllPedidosDTOByIdCliente(Integer idCliente) {

		List<Pedido> pedidos = pedidoRepository.findAllByIdCliente(idCliente);
		List<PedidoDTO> pedidosDto = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			List<ItemPedidoDTO> itemsDTO = new ArrayList<>();
			for (ItemPedido itemPedido : pedido.getItems()) {
				ItemPedidoDTO itemPedidoDto = modelMapper.map(itemPedido, ItemPedidoDTO.class);
				itemsDTO.add(itemPedidoDto);
			}
			PedidoDTO pedidoDto = modelMapper.map(pedido, PedidoDTO.class);
			pedidoDto.setItensPedido(itemsDTO);
			pedidosDto.add(pedidoDto);
		}

		return pedidosDto;
	}

	public PedidoDTO getPedidoDTOByIdCliente(Integer idCliente, Integer idPedido) {
		Pedido pedido = pedidoRepository.findPedidoByIdCliente(idPedido, idCliente);

		List<ItemPedidoDTO> itemsDTO = new ArrayList<>();

		for (ItemPedido itemPedido : pedido.getItems()) {
			ItemPedidoDTO itemPedidoDto = modelMapper.map(itemPedido, ItemPedidoDTO.class);
			itemsDTO.add(itemPedidoDto);
		}
		PedidoDTO pedidoDto = modelMapper.map(pedido, PedidoDTO.class);
		pedidoDto.setItensPedido(itemsDTO);
		return pedidoDto;
	}

	public PedidoDTO getPedidoDTOById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Pedido", id));
		return modelMapper.map(pedido, PedidoDTO.class);
	}

	public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();

		Cliente cliente = clienteRepository.findById(pedidoDTO.getIdCliente())
				.orElseThrow(() -> new ClienteNotFoundException(pedidoDTO.getIdCliente()));
		pedido.setCliente(cliente);
		pedido.setStatus("Pago");

		List<ItemPedido> items = new ArrayList<>();
		BigDecimal valorTotal = new BigDecimal("0.00");

		for (Integer itemPedidoId : pedidoDTO.getIdsItemPedido()) {
			ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId)
					.orElseThrow(() -> new NoSuchElementException("Item Pedido", itemPedidoId));
			itemPedido.setPedido(pedido);
			Produto produto = itemPedido.getProduto();
			produto.setQtdEstoque(produto.getQtdEstoque() - itemPedido.getQuantidade());
			
			if(produto.getQtdEstoque() < 0 ) {
				throw new EstoqueNegativoException(produto.getIdProduto());
			}
			valorTotal = valorTotal.add(itemPedido.getValorLiquido());
			items.add(itemPedido);
		}

		pedido.setItems(items);
		pedido.setValorTotal(valorTotal);
		pedido.setDataPedido(Date.from(Instant.now()));
		
		
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(items);

		List<ItemPedidoDTO> itemsDTO = new ArrayList<>();
		for (ItemPedido itemPedido : items) {
			ItemPedidoDTO itemPedidoDto = modelMapper.map(itemPedido, ItemPedidoDTO.class);
			itemsDTO.add(itemPedidoDto);
		}

		PedidoDTO pedidoSalvo = modelMapper.map(pedido, PedidoDTO.class);
		pedidoSalvo.setItensPedido(itemsDTO);
		pedidoSalvo.setIdPedido(pedido.getIdPedido());
		pedidoSalvo.setCliente(modelMapper.map(cliente, ClienteDTO.class));
		pedidoSalvo.setValorTotal(valorTotal);
		
		enviarRelatorio(pedidoSalvo);
       

		return pedidoSalvo;
	}
}
