package com.residencia.ecommerce.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.repositories.ProdutoRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class ProdutoDTO {

	@NotBlank(message = "O nome do produto não pode ser nulo")
	private String nome;

	private String descricao;

	@Positive(message = "O valor do produto não pode ser nulo")
	private BigDecimal valorUnitario;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer idCategoria;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer qtdEstoque;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@PastOrPresent(message = "A data cadastrada não pode estar no futuro")
	private Date dataCadastro;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private CategoriaDTO categoria;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idImagem;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String nomeImagem;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private byte[] imagem;
	
	private String url;

	public ProdutoDTO() {
		super();
	}

	
	public ProdutoDTO(@NotBlank(message = "O nome do produto não pode ser nulo") String nome, String descricao,
			@Positive(message = "O valor do produto não pode ser nulo") BigDecimal valorUnitario, Integer idCategoria,
			Integer qtdEstoque,
			@PastOrPresent(message = "A data cadastrada não pode estar no futuro") Date dataCadastro,
			CategoriaDTO categoria, Integer idImagem, String nomeImagem, byte[] imagem, String url) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.idCategoria = idCategoria;
		this.qtdEstoque = qtdEstoque;
		this.dataCadastro = dataCadastro;
		this.categoria = categoria;
		this.idImagem = idImagem;
		this.nomeImagem = nomeImagem;
		this.imagem = imagem;
		this.url = url;
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

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public Integer getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "ProdutoDTO [nome=" + nome + ", descricao=" + descricao + ", valorUnitario=" + valorUnitario
				+ ", idCategoria=" + idCategoria + ", qtdEstoque=" + qtdEstoque + ", dataCadastro=" + dataCadastro
				+ ", categoria=" + categoria + "]";
	}

}
