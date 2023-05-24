package com.trabalho.escola.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idInstrutor",
		scope =  Instrutor.class
)
@Entity
@Table(name = "tb_instrutor")
public class Instrutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInstrutor")
	private Integer idInstrutor;
	
	
	@Column(name = "rg")
	private Integer rg;
	
	
	@Column(name = "nome")
	private String nome;

	@OneToMany(mappedBy = "instrutor")
	private List<Turma> turmas;
	
	@OneToOne(mappedBy = "instrutor")
	private Telefone telefone;
	
	public Integer getIdInstrutor() {
		return idInstrutor;
	}

	public void setIdInstrutor(Integer idInstrutor) {
		this.idInstrutor = idInstrutor;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	@Override
	public String toString() {
		return "Instrutor [idInstrutor=" + idInstrutor + ", rg=" + rg + ", nome=" + nome + ", telefone=" + telefone + "]";
	}	

}
