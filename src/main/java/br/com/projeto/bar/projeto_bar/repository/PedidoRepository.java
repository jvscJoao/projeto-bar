package br.com.projeto.bar.projeto_bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.bar.projeto_bar.entity.Cliente;
import br.com.projeto.bar.projeto_bar.entity.Pedido;
import br.com.projeto.bar.projeto_bar.enums.Mesa;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 

    List<Pedido> findByCliente(Cliente cliente);
    
    List<Pedido> findByMesa(Mesa mesa);
}
