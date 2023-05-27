package com.residencia.trabalho_final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.trabalho_final.entites.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}