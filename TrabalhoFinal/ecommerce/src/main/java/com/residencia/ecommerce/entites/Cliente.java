package com.residencia.ecommerce.entites;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.residencia.ecommerce.entites.seguranca.Role;
import com.residencia.ecommerce.entites.seguranca.RoleEnum;

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
import jakarta.validation.constraints.Size;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente", scope = Cliente.class)


@Entity
@Table(name = "cliente", uniqueConstraints = { @UniqueConstraint(columnNames = "cpf"),
											   @UniqueConstraint(columnNames = "email")})
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;
	
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@NotBlank(message = "A senha não pode estar vazia")
	@Size(min = 8, message="A senha deve ter no mínimo 8 caracteres")
	@Size(max = 120, message="A senha deve ter menos que 120 caracteres")
	private String password;
	
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
	
	@Past(message = "A data de nascimento deve estar no passado")
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@OneToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	private Role role;
	
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

	 
}
