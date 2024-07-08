package br.com.projeto.bar.projeto_bar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.projeto.bar.projeto_bar.entity.Funcionario;
import br.com.projeto.bar.projeto_bar.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> searchAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario searchById(String id) {
        Optional<Funcionario> obj = funcionarioRepository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada!"));
    }

    public Funcionario insert(Funcionario funcionario) {
        funcionario.setId(null);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void deleteById(String id) {
        searchById(id);
        funcionarioRepository.deleteById(id);
    }
}
