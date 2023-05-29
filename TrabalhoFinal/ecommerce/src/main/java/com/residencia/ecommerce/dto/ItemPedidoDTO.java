package com.residencia.ecommerce.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.residencia.ecommerce.entites.Produto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ItemPedidoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idItemPedido;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer produtoID;
	
	@PositiveOrZero(message = "A quantidade não pode ser negativa")
	private Integer quantidade;

	@PositiveOrZero(message = "O desconto não pode ser negativo")
	private Double percentualDesconto;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Positive(message = "O preço de venda não pode ser nulo")
	private BigDecimal precoVenda;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private BigDecimal valorBruto;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private BigDecimal valorLiquido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Produto produto;

	public ItemPedidoDTO() {
		super();
	}

	public ItemPedidoDTO(Integer idItemPedido, Integer produtoID,
			@PositiveOrZero(message = "A quantidade não pode ser negativa") Integer quantidade,
			@PositiveOrZero(message = "O desconto não pode ser negativo") Double percentualDesconto,
			@Positive(message = "O preço de venda não pode ser nulo") BigDecimal precoVenda, BigDecimal valorBruto,
			BigDecimal valorLiquido, Produto produto) {
		super();
		this.idItemPedido = idItemPedido;
		this.produtoID = produtoID;
		this.quantidade = quantidade;
		this.percentualDesconto = percentualDesconto;
		this.precoVenda = precoVenda;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.produto = produto;
	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
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

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemPedidoDTO [idItemPedido=" + idItemPedido + ", produtoID=" + produtoID + ", quantidade=" + quantidade
				+ ", percentualDesconto=" + percentualDesconto + ", precoVenda=" + precoVenda + ", valorBruto="
				+ valorBruto + ", valorLiquido=" + valorLiquido + ", produto=" + produto + "]";
	}
	
}
