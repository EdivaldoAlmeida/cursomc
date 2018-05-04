package com.edivaldova.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edivaldova.cursomc.domain.Categoria;
import com.edivaldova.cursomc.domain.Cidade;
import com.edivaldova.cursomc.domain.Cliente;
import com.edivaldova.cursomc.domain.Endereco;
import com.edivaldova.cursomc.domain.Estado;
import com.edivaldova.cursomc.domain.ItemPedido;
import com.edivaldova.cursomc.domain.Pagamento;
import com.edivaldova.cursomc.domain.PagamentoComBoleto;
import com.edivaldova.cursomc.domain.PagamentoComCartao;
import com.edivaldova.cursomc.domain.Pedido;
import com.edivaldova.cursomc.domain.Produto;
import com.edivaldova.cursomc.domain.enums.EstadoPagamento;
import com.edivaldova.cursomc.domain.enums.TipoCliente;
import com.edivaldova.cursomc.repositories.CategoriaRepository;
import com.edivaldova.cursomc.repositories.CidadeRepository;
import com.edivaldova.cursomc.repositories.ClienteRepository;
import com.edivaldova.cursomc.repositories.EnderecoRepository;
import com.edivaldova.cursomc.repositories.EstadoRepository;
import com.edivaldova.cursomc.repositories.ItemPedidoRepository;
import com.edivaldova.cursomc.repositories.PagamentoRepository;
import com.edivaldova.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Criando as categorias para serem inseridas automaticamente ao banco
		Categoria cat1 = new Categoria(null, "Informática"); //null pq o bd irá gerar id automaticamente
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
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
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		//Salvando estados e cidades no bd. Estado antes pois é independente
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//A ordem da instanciação é importante, pois há entidades que dependem de outras.
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "387736726", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("9987-9779", "8798-8778"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Ilhotas", "345839398", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "600", "Sala 2", "Centro", "898797099", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//Salvando cliente primeiro, pois é independente
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		//Gerando pedidos
		
		//Formatando uma data/hora para o objeto instante
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		//Salvando pedido primeiro, pois é independente
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		//Criando itemPedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		//Salvando na base de dados
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
	
	
}
