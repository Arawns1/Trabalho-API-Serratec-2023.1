package com.residencia.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;


public class PedidoDTO {
	
	@FutureOrPresent(message = "A data do pedido não pode estar no passado")
	private Date dataPedido;
	
	@FutureOrPresent(message = "A data do envio não pode estar no passado")
	private Date dataEnvio;
	
	@FutureOrPresent(message = "A data da entrega não pode estar no passado")
	private Date dataEntrega;
	
	private String status;
	
	private BigDecimal valorTotal;
	
	private ClienteDTO cliente;
	
	private List<ItemPedidoDTO> items;
	
	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Date dataPedido, Date dataEnvio, Date dataEntrega, String status, BigDecimal valorTotal,
			ClienteDTO cliente, List<ItemPedidoDTO> items) {
		super();
		this.dataPedido = dataPedido;
		this.dataEnvio = dataEnvio;
		this.dataEntrega = dataEntrega;
		this.status = status;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.items = items;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}
}
