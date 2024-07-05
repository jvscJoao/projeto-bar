package br.com.projeto.bar.projeto_bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.bar.projeto_bar.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    
}
