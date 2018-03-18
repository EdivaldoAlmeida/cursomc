package com.edivaldova.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.domain.Cidade;
import com.edivaldova.cursomc.domain.Estado;
import com.edivaldova.cursomc.domain.Produto;
import com.edivaldova.cursomc.repositories.CategoriaRepository;
import com.edivaldova.cursomc.repositories.CidadeRepository;
import com.edivaldova.cursomc.repositories.EstadoRepository;
import com.edivaldova.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	
	//CategoriaRepository será responsável por salvar Categorias no BD
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired 
	private ProdutoRepository produtoReopository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Criando as categorias para serem serem inseridas automaticamente ao banco
		Categoria cat1 = new Categoria(null, "Informática"); //null pq o bd irá gerar id automaticamente
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 3000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//Associando os produtos às suas respectivaas categorias
		//1 - Quais produtos possuem cada categoria
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p1));
		
		//2 - A qual categoria pertence cada produto
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//Arrays.asList cria uma lista automaticamente com a quantidade necessária de itens
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoReopository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		/*---------------Instanciando cidades e estados-----------------------*/
		
		Estado est1 = new Estado(null, "Piauí");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Teresina", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//Adicionando todas as cidades pertencentes a seus estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		
		//Salvando estados e cidades no bd
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		
		
		
	}
	
	
}
