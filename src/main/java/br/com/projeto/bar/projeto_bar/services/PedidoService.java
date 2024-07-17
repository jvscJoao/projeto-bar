package br.com.projeto.bar.projeto_bar.services;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.projeto.bar.projeto_bar.entity.ItemPedido;
import br.com.projeto.bar.projeto_bar.entity.Pagamento;
import br.com.projeto.bar.projeto_bar.entity.Pedido;
import br.com.projeto.bar.projeto_bar.entity.Produto;
import br.com.projeto.bar.projeto_bar.enums.StatusPedido;
import br.com.projeto.bar.projeto_bar.enums.TipoPagamento;
import br.com.projeto.bar.projeto_bar.repository.ItemPedidoRepository;
import br.com.projeto.bar.projeto_bar.repository.PagamentoRepository;
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
    private final PagamentoRepository pagamentoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository,
            ProdutoService produtoService, ClienteService clienteService, FuncionarioService funcionarioService,
            PagamentoRepository pagamentoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.pagamentoRepository = pagamentoRepository;
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
        newObj.setStatus(pedido.getStatus());
        
        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            newObj.setCliente(clienteService.searchById(pedido.getCliente().getId()));
        }
        
        newObj.setFuncionario(funcionarioService.searchById(pedido.getFuncionario().getId()));
        if (pedido.getStatus().equals(StatusPedido.FIADO)) {
            if (pedido.getCliente() == null ) {
                throw new DataIntegrityViolationException("Pedido fiado precisa de um cliente cadastrado no sistema!");
            }
            newObj.setCliente(clienteService.searchById(pedido.getCliente().getId()));
        } else if (pedido.getStatus().equals(StatusPedido.FINALIZADO)) {
            Pagamento pagamento = pedido.getPagamento();
        
            if (pagamento.getTipo().equals(TipoPagamento.CARTAO) && (pagamento.getParcelas() <= 1)) {
                throw new DataIntegrityViolationException("Número de parcelas não pode ser menor que 2x!");
            }
            newObj.setPagamento(new Pagamento(pagamento.getTipo(), pagamento.getParcelas(), newObj));
        }

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

        if (newObj.getPagamento() != null) {
            newObj.getPagamento().setValor(newObj.getValorTotal());
            pagamentoRepository.save(newObj.getPagamento());
        }
        
        return newObj;
    }
    

    @Transactional
    public Pedido update(Long id, Pedido pedido) {
        Pedido pd = searchById(id);
        if (pd.getStatus() != StatusPedido.FIADO) {
            throw new DataIntegrityViolationException("Apenas pode ser alterado caso o pedido esteja com status Fiado!");
        }
        pd.setStatus(StatusPedido.FINALIZADO);
        Pagamento pagamento = pedido.getPagamento();
        
        if (pagamento.getTipo().equals(TipoPagamento.CARTAO)) {
            Integer parcelas = pagamento.getParcelas();
            if (parcelas == null || parcelas <= 1) {
                throw new DataIntegrityViolationException("Número de parcelas não pode ser menor que 2x!");
            }
        }
        pd.setPagamento(new Pagamento(pagamento.getTipo(), pagamento.getParcelas(), pd));
        pedidoRepository.save(pd);
        pagamentoRepository.save(pd.getPagamento());
        return pd;
    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.delete(searchById(id));
    }

}
