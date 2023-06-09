package com.residencia.ecommerce.entites;

import java.util.Date;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente", scope = Cliente.class)

@Entity
@Table(name = "cliente", uniqueConstraints = { @UniqueConstraint(columnNames = "cpf"),
		@UniqueConstraint(columnNames = "email") })
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Email(message = "E-mail inválido")
	@Column(name = "email")
	private String email;

	@NotBlank(message = "O nome não pode ser nulo")
	@Column(name = "nome_completo")
	private String nomeCompleto;

	@CPF(message = "CPF inválido")
	@Column(name = "cpf")
	private String cpf;

	@Pattern(regexp = "\\d{8,15}")
	@Column(name = "telefone")
	private String telefone;
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo" )
	@Past(message = "A data de nascimento deve estar no passado")
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@OneToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", email=" + email + ", nomeCompleto=" + nomeCompleto + ", cpf="
				+ cpf + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco
				+ "]";
	}
	
}
