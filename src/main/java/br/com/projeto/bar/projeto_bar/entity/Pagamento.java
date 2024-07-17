package br.com.projeto.bar.projeto_bar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projeto.bar.projeto_bar.enums.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAGAMENTOS")
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;
    private Double valor;
    private Integer parcelas;
    @OneToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    public Pagamento(TipoPagamento tipo, Integer parcelas, Pedido pedido) {
        this.tipo = tipo;
        this.valor = pedido.getValorTotal();
        this.parcelas = (parcelas == null) ? 0 : parcelas;
        this.pedido = pedido;
    }

    public Pagamento() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
