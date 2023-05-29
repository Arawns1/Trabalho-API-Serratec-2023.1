package com.residencia.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.residencia.ecommerce.entites.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query("FROM Pedido p where p.cliente.idCliente = :idCliente and p.idPedido = :idPedido")
	Pedido findPedidoByIdCliente(Integer idPedido, Integer idCliente);
	
	@Query("FROM Pedido p where p.cliente.idCliente = :idCliente")
	List<Pedido> findAllByIdCliente(Integer idCliente);
}