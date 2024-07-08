package br.com.projeto.bar.projeto_bar.entity;

import br.com.projeto.bar.projeto_bar.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String senha;
    private boolean ativo;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Funcionario() {}

    public Funcionario(String nome, String senha, boolean ativo, String cpf, Perfil perfil) {
        this.nome = nome;
        this.senha = senha;
        this.ativo = ativo;
        this.cpf = cpf;
        this.perfil = perfil;
    }

    public Funcionario(String nome, String senha, String cpf, Perfil perfil) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.ativo = true;
        this.perfil = perfil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }



}
