package com.trabalho.escola.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_instrutor")
public class Instrutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInstrutor;
	
	@Column(name = "rg")
	private Integer rg;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "instrutor")
    private List<Turma> turmas;
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Integer getId() {
		return idInstrutor;
	}

	public void setId(Integer id) {
		this.idInstrutor = id;
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
	
}
