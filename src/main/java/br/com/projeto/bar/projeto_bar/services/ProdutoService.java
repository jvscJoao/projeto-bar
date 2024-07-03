package br.com.projeto.bar.projeto_bar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.projeto.bar.projeto_bar.entity.Produto;
import br.com.projeto.bar.projeto_bar.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> searchAll() {
        Sort sort = Sort.by("nome").ascending();
        return produtoRepository.findAll(sort);
    }

    public Produto searchById(String id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada!"));
    }

    public Produto insert(Produto obj) {
        return produtoRepository.save(obj);
    }

    public Produto update(Produto obj) {
        return produtoRepository.save(obj);
    }
    
    public void delete(String id) {
        produtoRepository.deleteById(id);;
    }
}
