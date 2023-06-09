package com.residencia.ecommerce.entites;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "idPedido",
  scope = Pedido.class
)
@Entity
@Table(name = "pedido")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pedido")
  private Integer idPedido;

  @Column(name = "data_pedido")
  private Date dataPedido;

  @FutureOrPresent(message = "A data do envio não pode estar no passado")
  @Column(name = "data_envio")
  private Date dataEnvio;

  @FutureOrPresent(message = "A data da entrega não pode estar no passado")
  @Column(name = "data_entrega")
  private Date dataEntrega;

  @Column(name = "status")
  private String status;

  @Column(name = "valor_total")
  private BigDecimal valorTotal;

  @ManyToOne
  @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
  private Cliente cliente;

  @JsonIgnore
  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> items;

public Integer getIdPedido() {
	return idPedido;
}

public void setIdPedido(Integer idPedido) {
	this.idPedido = idPedido;
}

public Date getDataPedido() {
	return dataPedido;
}

public void setDataPedido(Date dataPedido) {
	this.dataPedido = dataPedido;
}

public Date getDataEnvio() {
	return dataEnvio;
}

public void setDataEnvio(Date dataEnvio) {
	this.dataEnvio = dataEnvio;
}

public Date getDataEntrega() {
	return dataEntrega;
}

public void setDataEntrega(Date dataEntrega) {
	this.dataEntrega = dataEntrega;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public BigDecimal getValorTotal() {
	return valorTotal;
}

public void setValorTotal(BigDecimal valorTotal) {
	this.valorTotal = valorTotal;
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public List<ItemPedido> getItems() {
	return items;
}

public void setItems(List<ItemPedido> items) {
	this.items = items;
}

@Override
public int hashCode() {
	return Objects.hash(idPedido);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Pedido other = (Pedido) obj;
	return Objects.equals(idPedido, other.idPedido);
}
  
}
