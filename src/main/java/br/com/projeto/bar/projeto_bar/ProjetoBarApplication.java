package br.com.projeto.bar.projeto_bar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projeto.bar.projeto_bar.entity.Cliente;
import br.com.projeto.bar.projeto_bar.entity.Funcionario;
import br.com.projeto.bar.projeto_bar.entity.ItemPedido;
import br.com.projeto.bar.projeto_bar.entity.Pedido;
import br.com.projeto.bar.projeto_bar.entity.Produto;
import br.com.projeto.bar.projeto_bar.enums.Mesa;
import br.com.projeto.bar.projeto_bar.enums.Perfil;
import br.com.projeto.bar.projeto_bar.enums.ProdutoCategoria;
import br.com.projeto.bar.projeto_bar.repository.ClienteRepository;
import br.com.projeto.bar.projeto_bar.repository.FuncionarioRepository;
import br.com.projeto.bar.projeto_bar.repository.ItemPedidoRepository;
import br.com.projeto.bar.projeto_bar.repository.PedidoRepository;
import br.com.projeto.bar.projeto_bar.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetoBarApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBarApplication.class, args);
	}

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		Produto produto1 = new Produto("Skoll", "Faz o L", 12.00, ProdutoCategoria.CERVEJAS);
		Produto produto2 = new Produto("Espetinho de Picanha", "Faz o L", 100.00, ProdutoCategoria.PETISCOS);
		produtoRepository.saveAll(Arrays.asList(produto1, produto2));

		Cliente cliente1 = new Cliente("Janja", "999.666.999.96");
		cliente1.setTelefones(Arrays.asList("(61) 9 1396-6913", "(69) 9 9999-6666"));
		clienteRepository.saveAll(Arrays.asList(cliente1));

		Funcionario funcionario1 = new Funcionario("Haddad", "impostoDoAmor", "131.313.131.31", Perfil.ATENDENTE);
		Funcionario funcionario2 = new Funcionario("Lula", "picanha", "133.133.331.13", Perfil.ADMIN);
		funcionarioRepository.saveAll(Arrays.asList(funcionario1, funcionario2));

		Pedido pedido1 = new Pedido("Do the L", funcionario1, Mesa.N_1);
		pedidoRepository.saveAll(Arrays.asList(pedido1));

		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 5);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto2, 5);
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2));

	}
}
