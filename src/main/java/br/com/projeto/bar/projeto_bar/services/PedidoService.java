package br.com.projeto.bar.projeto_bar.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.projeto.bar.projeto_bar.entity.ItemPedido;
import br.com.projeto.bar.projeto_bar.entity.Pedido;
import br.com.projeto.bar.projeto_bar.entity.Produto;
import br.com.projeto.bar.projeto_bar.repository.ItemPedidoRepository;
import br.com.projeto.bar.projeto_bar.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository, ProdutoService produtoService, ClienteService clienteService, FuncionarioService funcionarioService) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
    }

    public List<Pedido> searchAll() {
        return pedidoRepository.findAll();
    }

    public Pedido searchById(Long id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada!"));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        Pedido newObj = new Pedido();
        newObj.setNome(pedido.getNome());
        newObj.setMesa(pedido.getMesa());
        
        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            newObj.setCliente(clienteService.searchById(pedido.getCliente().getId()));
        }
        
        newObj.setFuncionario(funcionarioService.searchById(pedido.getFuncionario().getId()));

        newObj = pedidoRepository.save(newObj);

        List<ItemPedido> itensSalvos = new ArrayList<>();
        for (ItemPedido ip : pedido.getItemPedidos()) {
            ip.setMesa(newObj.getMesa());
            ip.setPedido(newObj);
            Produto produto = produtoService.searchById(ip.getProduto().getId());
            ip.setProduto(produto);
            itensSalvos.add(itemPedidoRepository.save(ip));
        }
        
        newObj.setItemPedidos(itensSalvos);
        
        return newObj;
    }

    @Transactional
    public Pedido update(Pedido pedido) {
        searchById(pedido.getId());
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.delete(searchById(id));
    }

}
