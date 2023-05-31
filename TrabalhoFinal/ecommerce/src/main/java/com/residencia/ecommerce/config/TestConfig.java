package com.residencia.ecommerce.config;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.residencia.ecommerce.entites.Categoria;
import com.residencia.ecommerce.entites.Cliente;
import com.residencia.ecommerce.entites.Endereco;
import com.residencia.ecommerce.entites.ItemPedido;
import com.residencia.ecommerce.entites.Pedido;
import com.residencia.ecommerce.entites.Produto;
import com.residencia.ecommerce.repositories.CategoriaRepository;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.EnderecoRepository;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.PedidoRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run(String... args) throws Exception {
		/*
		Endereco end1 = new Endereco();
		end1.setRua("Rua do Imperador");
		end1.setBairro("Centro");
		end1.setCep("25600000");
		end1.setCidade("Petrópolis");
		end1.setComplemento("Casa");
		end1.setNumero(1000);
		end1.setUf("RJ");
		enderecoRepository.save(end1);

		Cliente cli1 = new Cliente();
		cli1.setNomeCompleto("Carlos Santos");
		cli1.setCpf("123456789");

		cli1.setDataNascimento(sdf.parse("25/05/2023"));
		cli1.setEmail("carlos@email.com");
		cli1.setTelefone("981234567");
		cli1.setEndereco(end1);
		clienteRepository.save(cli1);

		Categoria cat1 = new Categoria();
		cat1.setNome("Eletronicos");
		cat1.setDescricao("Eletronicos MUITO TECHS! ");

		Categoria cat2 = new Categoria();
		cat2.setNome("Celulares");
		cat2.setDescricao("Celulares mais tops! Brabissimos ");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		Produto p1 = new Produto();
		p1.setNome("Iphone 32 max");
		p1.setDataCadastro(sdf.parse("25/05/2023"));
		p1.setDescricao("Iphone ultimo lançamento!");
		p1.setQtdEstoque(10);
		p1.setValorUnitario(new BigDecimal("9912.122231312"));
		p1.setCategoria(cat2);
		p1.setImagem(null);
		produtoRepository.save(p1);

		Produto p2 = new Produto();
		p2.setNome("Maquina de Lavar");
		p2.setDataCadastro(sdf.parse("25/05/2023"));
		p2.setDescricao("Lava tudo");
		p2.setQtdEstoque(10);
		p2.setValorUnitario(new BigDecimal(1000.0));
		p2.setCategoria(cat1);
		p2.setImagem(null);
		produtoRepository.save(p2);
	
		Pedido ped1 = new Pedido();
		ped1.setDataPedido(sdf.parse("24/05/2023"));
		ped1.setDataEnvio(sdf.parse("25/05/2023"));
		ped1.setDataEntrega(sdf.parse("26/05/2023"));
		ped1.setStatus("Pago");
		ped1.setCliente(cli1);
		pedidoRepository.save(ped1);
		
		ItemPedido ip1 = new ItemPedido();
		ip1.setProduto(p2);
		ip1.setPercentualDesconto(0.1);
		ip1.setPrecoVenda(new BigDecimal(1000.0));
		ip1.setQuantidade(2);
		ip1.setPedido(ped1);
		itemPedidoRepository.save(ip1);
		
		ItemPedido ip2 = new ItemPedido();
		ip2.setProduto(p1);
		ip2.setPercentualDesconto(0.1);
		ip2.setPrecoVenda(new BigDecimal(2000.0));
		ip2.setQuantidade(2);
		ip2.setPedido(ped1);
		itemPedidoRepository.save(ip2);
	
		Pedido ped2 = new Pedido();
		ped2.setDataPedido(sdf.parse("24/05/2023"));
		ped2.setDataEnvio(sdf.parse("25/05/2023"));
		ped2.setDataEntrega(sdf.parse("26/05/2023"));
		ped2.setStatus("Pago");
		ped2.setCliente(cli1);
		pedidoRepository.save(ped2);
		
		ItemPedido ip3 = new ItemPedido();
		ip3.setProduto(p1);
		ip3.setPercentualDesconto(0.1);
		ip3.setPrecoVenda(new BigDecimal(2000.0));
		ip3.setQuantidade(2);
		ip3.setPedido(ped2);
		itemPedidoRepository.save(ip3);
	*/
	}

}
