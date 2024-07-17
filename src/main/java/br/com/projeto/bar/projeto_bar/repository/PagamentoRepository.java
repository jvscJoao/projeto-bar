package br.com.projeto.bar.projeto_bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.bar.projeto_bar.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    
}
