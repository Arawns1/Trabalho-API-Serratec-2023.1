package com.residencia.ecommerce.dto;

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
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idImagem", scope = ImagemDTO.class)

@Entity
@Table(name = "imagem")
public class ImagemDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagem")
	private Integer idImagem;
	
	@JsonIgnore
	@Lob
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private byte[] imagemDados;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String tipo;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String nome;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String url;

	public ImagemDTO() {
		super();
	}

	public ImagemDTO(Integer idImagem, byte[] imagemDados, String tipo, String nome, String url) {
		super();
		this.idImagem = idImagem;
		this.imagemDados = imagemDados;
		this.tipo = tipo;
		this.nome = nome;
		this.url = url;
	}



	public Integer getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public byte[] getImagemDados() {
		return imagemDados;
	}

	public void setImagemDados(byte[] imagemDados) {
		this.imagemDados = imagemDados;
	}

	@Override
	public String toString() {
		return "ImagemDTO [idImagem=" + idImagem + ", imagemDados=" + Arrays.toString(imagemDados) + ", tipo=" + tipo
				+ ", nome=" + nome + ", url=" + url + "]";
	}

}
