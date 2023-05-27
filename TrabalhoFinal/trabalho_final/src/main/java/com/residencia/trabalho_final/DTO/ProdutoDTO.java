package com.residencia.trabalho_final.DTO;

import java.math.BigDecimal;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProdutoDTO {
	
	@NotBlank(message = "O nome do produto não pode ser nulo")
	private String nome;
	
	private String descricao;
	
	@Positive(message = "O valor do produto não pode ser nulo")
	private BigDecimal valorUnitario;
	
	@Lob
	private byte[] imagem ;
	
	private Integer idCategoria;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(@NotBlank(message = "O nome do produto não pode ser nulo") String nome, String descricao,
			@Positive(message = "O valor do produto não pode ser nulo") BigDecimal valorUnitario, byte[] imagem,
			Integer idCategoria) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.imagem = imagem;
		this.idCategoria = idCategoria;
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

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
