package com.residencia.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoDTO {
	private Integer idEndereco;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String cep;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String rua;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String bairro;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String cidade;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer numero;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String complemento;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String uf;
	
	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(Integer idEndereco, String cep, String rua, String bairro, String cidade, Integer numero,
			String complemento, String uf) {
		super();
		this.idEndereco = idEndereco;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.complemento = complemento;
		this.uf = uf;
	}
	
	public Integer getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "EnderecoDTO [idEndereco=" + idEndereco + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", numero=" + numero + ", complemento=" + complemento + ", uf=" + uf + "]";
	}
	
}
