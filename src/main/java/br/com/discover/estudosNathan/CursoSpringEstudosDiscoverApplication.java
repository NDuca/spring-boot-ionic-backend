package br.com.discover.estudosNathan;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.discover.estudosNathan.domain.Categoria;
import br.com.discover.estudosNathan.domain.Cidade;
import br.com.discover.estudosNathan.domain.Cliente;
import br.com.discover.estudosNathan.domain.Endereco;
import br.com.discover.estudosNathan.domain.Estado;
import br.com.discover.estudosNathan.domain.ItemPedido;
import br.com.discover.estudosNathan.domain.Pagamento;
import br.com.discover.estudosNathan.domain.PagamentoComBoleto;
import br.com.discover.estudosNathan.domain.PagamentoComCartao;
import br.com.discover.estudosNathan.domain.Pedido;
import br.com.discover.estudosNathan.domain.Produto;
import br.com.discover.estudosNathan.domain.enums.EstadoPagamento;
import br.com.discover.estudosNathan.domain.enums.TipoCliente;
import br.com.discover.estudosNathan.repositories.CategoriaRepository;
import br.com.discover.estudosNathan.repositories.CidadeRepository;
import br.com.discover.estudosNathan.repositories.ClienteRepository;
import br.com.discover.estudosNathan.repositories.EnderecoRepository;
import br.com.discover.estudosNathan.repositories.EstadoRepository;
import br.com.discover.estudosNathan.repositories.ItemPedidoRepository;
import br.com.discover.estudosNathan.repositories.PagamentoRepository;
import br.com.discover.estudosNathan.repositories.PedidoRepository;
import br.com.discover.estudosNathan.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringEstudosDiscoverApplication implements CommandLineRunner{
// CommandLineRunner permite implementar um metodo auxiliar
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
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
		SpringApplication.run(CursoSpringEstudosDiscoverApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "405.889.984-01", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2789-9017", "5690-9010"));
		
		Endereco e1 = new Endereco(null, "Rua das Pamonhas", "89", "Apto 35, 3 andar", "Capichimba", "09944-903", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Dolce Cabanna", "300", "Casa 2", "Vancover", "09674-903", cli1, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/11/2017 10:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("15/12/2017 15:30"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/12/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
	}
}
