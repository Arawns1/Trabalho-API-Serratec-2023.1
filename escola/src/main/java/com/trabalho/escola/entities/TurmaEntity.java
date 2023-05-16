package com.trabalho.escola.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_turma")
public class TurmaEntity {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	private Integer Id;

	@Column(name = "nomedisciplina")
	private String nomeDisciplina; 
	
	@Column(name = "diasemana")
	private String diaSemana;
	
	@Column(name = "idinstrutor")
	private Integer idInstrutor ;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Integer getIdInstrutor() {
		return idInstrutor;
	}

	public void setIdInstrutor(Integer idInstrutor) {
		this.idInstrutor = idInstrutor;
	}
	
	
}
