package com.residencia.ecommerce.entites;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idImagem", scope = Imagem.class)

@Entity
@Table(name = "imagem")
public class Imagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_imagem")
	private Integer idImagem;
	
	@JsonIgnore
	@Lob
	@Column(name = "imagem_bytes", columnDefinition="BLOB")
	private byte[] dados;
	
	private String tipo;
	
	private String nome;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String url;
	
	@OneToOne(mappedBy = "imagem")
	private Produto produto;

	public Imagem() {
		super();
	}

	public Imagem(Integer idImagem, byte[] dados, String tipo, String nome, Produto produto) {
		super();
		this.idImagem = idImagem;
		this.dados = dados;
		this.tipo = tipo;
		this.nome = nome;
		this.produto = produto;
	}
	public Integer getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Imagem [idImagem=" + idImagem + ", dados=" + Arrays.toString(dados) + ", tipo=" + tipo + ", nome="
				+ nome + ", produto=" + produto + "]";
	}

}
