package com.residencia.trabalho_final.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iditempedido")
	private Integer id_item_pedido;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "precovenda")
	private Double preco_venda;
	
	@Column(name = "percentualdesconto")
	private Double percentual_desconto;
	
	@Column(name = "valorbruto")
	private Double valor_bruto;
	
	@Column(name = "valorliquido")
	private Double valor_liquido;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "idproduto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "idpedido")
	private Pedido pedido;


	public Integer getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(Integer id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco_venda() {
		preco_venda = produto.getValor_unitario();
		return preco_venda;
	}

	public void setPreco_venda(Double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Double getPercentual_desconto() {
		return percentual_desconto;
	}

	public void setPercentual_desconto(Double percentual_desconto) {
		this.percentual_desconto = percentual_desconto;
	}

	public Double getValor_bruto() {
		valor_bruto = produto.getValor_unitario() * quantidade;
		return valor_bruto;
	}

	public void setValor_bruto(Double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public Double getValor_liquido() {
		valor_liquido = valor_bruto - (valor_bruto * (percentual_desconto/100));
		return valor_liquido;
	}

	public void setValor_liquido(Double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	

}
