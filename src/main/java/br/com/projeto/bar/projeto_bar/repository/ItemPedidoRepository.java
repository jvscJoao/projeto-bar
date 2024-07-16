package br.com.projeto.bar.projeto_bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.bar.projeto_bar.entity.Funcionario;
import br.com.projeto.bar.projeto_bar.entity.ItemPedido;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {


    List<ItemPedido> findByPedidoFuncionario(Funcionario funcionario);

}
