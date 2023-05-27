package com.residencia.ecommerce.DTO;

import jakarta.validation.constraints.PositiveOrZero;

public class EnderecoDTO {
	private String cep;
	private String complemento;
	
	@PositiveOrZero(message = "O número não pode ser nulo")
	private Integer numero ;
	
	private ClienteDTO cliente;
	
	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(String cep, String complemento, Integer numero, ClienteDTO cliente) {
		super();
		this.cep = cep;
		this.complemento = complemento;
		this.numero = numero;
		this.cliente = cliente;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
}
