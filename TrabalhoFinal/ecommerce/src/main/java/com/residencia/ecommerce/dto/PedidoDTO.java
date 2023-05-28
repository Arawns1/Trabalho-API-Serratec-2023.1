package com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.FutureOrPresent;


public class PedidoDTO {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idPedido;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date dataPedido;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@FutureOrPresent(message = "A data do envio não pode estar no passado")
	private Date dataEnvio;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@FutureOrPresent(message = "A data da entrega não pode estar no passado")
	private Date dataEntrega;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String status;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private BigDecimal valorTotal;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer idCliente;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ClienteDTO cliente;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Integer> idsItemPedido;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<ItemPedidoDTO> itensPedido;
	
	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Integer idCliente, List<Integer> idsItemPedido) {
		super();
		this.idCliente = idCliente;
		this.idsItemPedido = idsItemPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public List<Integer> getIdsItemPedido() {
		return idsItemPedido;
	}

	public void setIdsItemPedido(List<Integer> idsItemPedido) {
		this.idsItemPedido = idsItemPedido;
	}

	public List<ItemPedidoDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
}
