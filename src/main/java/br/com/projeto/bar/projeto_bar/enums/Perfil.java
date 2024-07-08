package br.com.projeto.bar.projeto_bar.enums;

public enum Perfil {
    ADMIN(1),
    ATENDENTE(2);

    private Integer id;

    private Perfil(Integer id) {
        this.id = id;
    }

}
