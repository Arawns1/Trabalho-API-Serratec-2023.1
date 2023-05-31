package com.residencia.ecommerce.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

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
			BigDecimal valorBruto = itemPedidoDTO.getValorBruto();
			String.format("%s.2f", Double.toString(valorBruto.doubleValue()));
			String.format("%s.2f", Double.toString(precoVenda.doubleValue()));
			String.format("%s.2f", Double.toString(valorLiquido.doubleValue()));
			htmlItemsPedido += "<tr>";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+id+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+nome+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>R$ "+precoVenda+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>"+quantidade+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>% "+percentualDesconto+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>R$ "+valorBruto+"</td>\r\n";
			htmlItemsPedido += "<td style='border:1px solid #000000;'>R$ "+valorLiquido+"</td>\r\n";
			htmlItemsPedido += "</tr>";
			};
		String htmlCliente = "";
		Optional<Cliente> cliente = clienteRepository.findById(pedidoDTO.getIdCliente());
		htmlCliente += "<table style='margin-left: auto; margin-right:auto; border-collapse:collapse; border:1px solid #000000;'>";
		htmlCliente += "<td><h4> Nome: "+ pedidoDTO.getCliente().getNomeCompleto() +"</h4></td>\r\n";
		htmlCliente += "<td><h4> CPF: "+ pedidoDTO.getCliente().getCpf() +"</h4></td>\r\n";
		htmlCliente += "<td><h4> Email: "+ pedidoDTO.getCliente().getEmail() +"</h4></td>\r\n";
		htmlCliente += "</table>";
		
		String htmlEndereço = "";
		htmlEndereço += "<hr/>\r\n";
		htmlEndereço += "<br/>\r\n";
		htmlEndereço += "<h2 style='color:green; text-align:center'> Endereço de entrega: </h2>\r\n";
		htmlEndereço += "<h4> CEP: "+ pedidoDTO.getCliente().getEndereco().getCep() +"</h4>\r\n";
		htmlEndereço += "<h4> UF: "+ pedidoDTO.getCliente().getEndereco().getUf() +"</h4>\r\n";
		htmlEndereço += "<h4> Bairro: "+ pedidoDTO.getCliente().getEndereco().getBairro() +"</h4>\r\n";
		htmlEndereço += "<h4> Rua: "+ pedidoDTO.getCliente().getEndereco().getRua() +"</h4>\r\n";
		htmlEndereço += "<h4> Numero: "+ pedidoDTO.getCliente().getEndereco().getNumero() +"</h4>\r\n";
		htmlEndereço += "<h4> Complemento: "+ pedidoDTO.getCliente().getEndereco().getComplemento() +"</h4>\r\n";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy HH:mm");
		
		Calendar data = new GregorianCalendar(pedidoDTO.getDataPedido().getYear(), pedidoDTO.getDataPedido().getMonth(),pedidoDTO.getDataPedido().getDay(),pedidoDTO.getDataPedido().getHours(),pedidoDTO.getDataPedido().getMinutes());
	
		
		String htmlPedido = "";
		htmlPedido += "<h4> ID do pedido: "+ pedidoDTO.getIdPedido() +"</h4>\r\n";
		htmlPedido += "<h4> Data do pedido: "+ sdf.format(data.getTime()) +"</h4>\r\n";
		String.format("%s.2f", Double.toString(pedidoDTO.getValorTotal().doubleValue()));
		htmlPedido += "<h4> Valor Total: R$ "+ pedidoDTO.getValorTotal() +"</h4>\r\n";

		String html = "<html>\r\n"
				+ "	<body>\r\n"
				+ "		<div style='text-align: center'>"
				+ "     	<img src='https://cdn.discordapp.com/attachments/1094644010372583526/1113219670900744312/logo.png'>"
				+ "		</div>"
				+ "		<h1 style='color:green; text-align:center'> Pedido cadastrado com sucesso! </h1>\r\n"
				+ "     <hr/>\r\n"
				+ "     <br/>\r\n"
				+ "     <br/>\r\n"
				+ "		<h2 style='color:green; text-align:center'> Dados do Cliente: </h2>\r\n"
				+ htmlCliente
				+ htmlEndereço
				+ "     <hr/>\r\n"
				+ "     <br/>\r\n"
				+ "     <br/>\r\n"
				+ "		<h2 style='color:green; text-align:center'> Items: </h2>\r\n"
				+ "     <table style='margin-left: auto; margin-right:auto; border-collapse:collapse; border:1px solid #000000;'>\r\n"
				+ "			<tr>"
				+ "		    	<th>CODIGO |</th>"
				+ "			    <th> NOME |</th>"
				+ "			    <th> PREÇO |</th>"
				+ "			    <th> QUANTIDADE |</th>"
				+ "			    <th> DESCONTO |</th>"
				+ "			    <th> VALOR BRUTO |</th>"
				+ "			    <th> VALOR LIQUIDO</th>"
				+ "			 </tr>"
				+ htmlItemsPedido
				+ "     </table>\r\n"
				+ "     <br/>\r\n"
				+ "     <hr/>\r\n"
				+ "     <br/>\r\n"
				+ "     <br/>\r\n"
				+ "		<h2 style='color:green; text-align:center'> Pedido: </h2>\r\n"	
				+ htmlPedido
				+ "	</body>\r\n"
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
