package com.avantetech.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.avantetech.cursomc.domain.Categoria;
import com.avantetech.cursomc.domain.Cidade;
import com.avantetech.cursomc.domain.Cliente;
import com.avantetech.cursomc.domain.Endereco;
import com.avantetech.cursomc.domain.Estado;
import com.avantetech.cursomc.domain.Produto;
import com.avantetech.cursomc.domain.enums.TipoCliente;
import com.avantetech.cursomc.repositories.CategoriaRepository;
import com.avantetech.cursomc.repositories.CidadeRepository;
import com.avantetech.cursomc.repositories.ClienteRepository;
import com.avantetech.cursomc.repositories.EnderecoRepository;
import com.avantetech.cursomc.repositories.EstadoRepository;
import com.avantetech.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
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
	

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 200.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
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
		
		
		Cliente clie1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "3637899415",TipoCliente.PESSOAFISICA);
		clie1.getTelefones().addAll(Arrays.asList("998661684" , "37996554"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apt 200", "alvorada", "35572038", clie1, c1);
		Endereco e2 = new Endereco(null, "Rua Sp", "100", "casa", "centro", "3557000", clie1, c2);
		
		clie1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(clie1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
		
		
	}
	
	

}

