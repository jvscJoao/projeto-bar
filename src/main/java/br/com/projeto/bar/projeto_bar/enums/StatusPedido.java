package br.com.projeto.bar.projeto_bar.enums;

public enum StatusPedido {

    FINALIZADO(1),
    FIADO(2);

    private Integer id;

    private StatusPedido(Integer id) {
        this.id = id;
    }
    
}
