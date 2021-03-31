package com.avantetech.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avantetech.cursomc.domain.Categoria;
import com.avantetech.cursomc.domain.Cidade;
import com.avantetech.cursomc.domain.Cliente;
import com.avantetech.cursomc.domain.Endereco;
import com.avantetech.cursomc.domain.Estado;
import com.avantetech.cursomc.domain.ItemPedido;
import com.avantetech.cursomc.domain.Pagamento;
import com.avantetech.cursomc.domain.PagamentoComBoleto;
import com.avantetech.cursomc.domain.PagamentoComCartao;
import com.avantetech.cursomc.domain.Pedido;
import com.avantetech.cursomc.domain.Produto;
import com.avantetech.cursomc.domain.enums.EstadoPagamento;
import com.avantetech.cursomc.domain.enums.TipoCliente;
import com.avantetech.cursomc.repositories.CategoriaRepository;
import com.avantetech.cursomc.repositories.CidadeRepository;
import com.avantetech.cursomc.repositories.ClienteRepository;
import com.avantetech.cursomc.repositories.EnderecoRepository;
import com.avantetech.cursomc.repositories.EstadoRepository;
import com.avantetech.cursomc.repositories.ItemPedidoRepository;
import com.avantetech.cursomc.repositories.PagamentoRepository;
import com.avantetech.cursomc.repositories.PedidoRepository;
import com.avantetech.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@Autowired
	private CidadeRepository CidadeRepository;
	
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Eletronica");
		Categoria cat4 = new Categoria(null, "Moveis");
		Categoria cat5 = new Categoria(null, "Cadeira");
		Categoria cat6 = new Categoria(null, "Tosa");
		Categoria cat7 = new Categoria(null, "Decoração");
		Categoria cat8 = new Categoria(null, "Lazer");
		Categoria cat9 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 600.00);
		Produto p2 = new Produto(null, "Impressora",10.00);
		Produto p3 = new Produto(null, "Mouse", 180.00);
		Produto p4 = new Produto(null, "MDF", 810.00);
		Produto p5 = new Produto(null, "Chapa de Aço", 120.00);
		Produto p6 = new Produto(null, "Shampoo", 80.00);
		Produto p7 = new Produto(null, "vaso de flor", 810.00);
		Produto p8 = new Produto(null, "pcinas", 880.00);
		Produto p9 = new Produto(null, "2One2 Vip", 840.00);
		

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p3));
		cat4.getProdutos().addAll(Arrays.asList(p4));
		cat5.getProdutos().addAll(Arrays.asList(p5));
		cat6.getProdutos().addAll(Arrays.asList(p6));
		cat7.getProdutos().addAll(Arrays.asList(p7));
		cat8.getProdutos().addAll(Arrays.asList(p8));
		cat9.getProdutos().addAll(Arrays.asList(p9));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p4.getCategorias().addAll(Arrays.asList(cat4));
		p5.getCategorias().addAll(Arrays.asList(cat5));
		p6.getCategorias().addAll(Arrays.asList(cat6));
		p7.getCategorias().addAll(Arrays.asList(cat7));
		p8.getCategorias().addAll(Arrays.asList(cat8));
		p9.getCategorias().addAll(Arrays.asList(cat9));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
	
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		CidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente clie1 = new Cliente(null,"Maria Silva", "pablogui1611@gmail.com", "3637899415",TipoCliente.PESSOAFISICA);
		clie1.getTelefones().addAll(Arrays.asList("998661684" , "37996554"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apt 200", "alvorada", "35572038", clie1, c1);
		Endereco e2 = new Endereco(null, "Rua Sp", "100", "casa", "centro", "3557000", clie1, c2);
		
		clie1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(clie1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), clie1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), clie1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		clie1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1 , 600.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2 , 360.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1 , 10.0);

		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
