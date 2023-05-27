package com.residencia.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entites.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}