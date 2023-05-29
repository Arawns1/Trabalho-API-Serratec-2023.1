package com.residencia.ecommerce.dto;

import java.util.List;

public class RelatorioDTO {

	
	private List<ItemPedidoDTO> itemPedidoDTO;
	private PedidoDTO pedidoDTO;
	
	public RelatorioDTO() {
		super();
	}
	
	public RelatorioDTO(List<ItemPedidoDTO> itemPedidoDTO, PedidoDTO pedidoDTO) {
		super();
		this.itemPedidoDTO = itemPedidoDTO;
		this.pedidoDTO = pedidoDTO;
	}

	public List<ItemPedidoDTO> getItemPedidoDTO() {
		return itemPedidoDTO;
	}
	public void setItemPedidoDTO(List<ItemPedidoDTO> itemPedidoDTO) {
		this.itemPedidoDTO = itemPedidoDTO;
	}
	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}
	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}
	@Override
	public String toString() {
		return "RelatorioDTO [itemPedidoDTO=" + itemPedidoDTO + ", pedidoDTO=" + pedidoDTO + "]";
	}
}
