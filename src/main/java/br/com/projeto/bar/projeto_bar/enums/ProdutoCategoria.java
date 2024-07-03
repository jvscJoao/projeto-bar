package br.com.projeto.bar.projeto_bar.enums;

public enum ProdutoCategoria {
    CERVEJAS(1),
    DRINKS(2),
    WHISKIES(3),
    OUTRASBEBIDAS(4),
    COMBOS(5),
    PETISCOS(6),
    OUTROS(7);

    private Integer id;

    private ProdutoCategoria(Integer id) {
        this.id = id;
    }
}
