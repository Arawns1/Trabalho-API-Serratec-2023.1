package com.residencia.trabalho_final.entites;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProduto", scope = Produto.class)

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  
	@Column(name = "id_produto")
	private Integer idProduto;
	
	@NotBlank(message = "O nome do produto não pode ser nulo")
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;
	
	@PastOrPresent(message = "A data cadastrada não pode estar no futuro")
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@Positive(message = "O valor do produto não pode ser nulo")
	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;
	
	@Lob
	@Column(name = "imagem")
	private byte[] imagem;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	private Categoria categoria;
	
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome + ", descricao=" + descricao + ", qtdEstoque="
				+ qtdEstoque + ", dataCadastro=" + dataCadastro + ", valorUnitario=" + valorUnitario +"]";
	}

}
