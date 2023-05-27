package com.residencia.trabalho_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.trabalho_final.entites.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}