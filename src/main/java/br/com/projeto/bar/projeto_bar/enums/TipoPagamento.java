package br.com.projeto.bar.projeto_bar.enums;

public enum TipoPagamento {

    DINHEIRO(1),
    CARTAO(2);

    private Integer id;

    private TipoPagamento(Integer id) {
        this.id = id;
    }   
    
}
