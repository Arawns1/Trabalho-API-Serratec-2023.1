package com.residencia.trabalho_final.DTO;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {
	
	@NotBlank
	private String nome;
	private String descricao;
	
	public CategoriaDTO() {
		super();
	}
	
	public CategoriaDTO(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}
