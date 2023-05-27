package com.residencia.trabalho_final.DTO;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class ClienteDTO {
	
	@NotBlank(message = "O nome não pode ser nulo")
	private String nomeCompleto;
	
	@Email(message = "E-mail inválido")
	private String email;
	
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Pattern(regexp = "\\d{8,15}")
	private String telefone;
	
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo" )
	private Date dataNascimento; 
	
	private String cep;
	
	@PositiveOrZero(message = "O número não pode ser nulo")
	private Integer numero;
	
	private String complemento;
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO(@NotBlank(message = "O nome não pode ser nulo") String nomeCompleto,
			@Email(message = "E-mail inválido") String email, @CPF(message = "CPF inválido") String cpf,
			@Pattern(regexp = "\\d{8,15}") String telefone, @Past Date dataNascimento, String cep,
			@PositiveOrZero(message = "O número não pode ser nulo") Integer numero, String complemento) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

}
