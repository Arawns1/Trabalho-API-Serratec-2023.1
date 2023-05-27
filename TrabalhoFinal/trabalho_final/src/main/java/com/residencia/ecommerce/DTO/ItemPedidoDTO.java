package com.residencia.ecommerce.DTO;

import com.residencia.ecommerce.entites.Pedido;

import jakarta.validation.constraints.PositiveOrZero;

public class ItemPedidoDTO {
	
	private Integer produtoID;
	
	@PositiveOrZero(message = "A quantidade não pode ser negativa")
	private Integer quantidade;
	
	@PositiveOrZero(message = "O desconto não pode ser negativo")
	private Double percentualDesconto ;
	

	Pedido pedido;
	
	public ItemPedidoDTO() {
		super();
	}

	public Integer getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(Integer produtoID) {
		this.produtoID = produtoID;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
}
