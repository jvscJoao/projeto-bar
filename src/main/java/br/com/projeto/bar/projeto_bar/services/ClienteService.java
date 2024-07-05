package br.com.projeto.bar.projeto_bar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.projeto.bar.projeto_bar.entity.Cliente;
import br.com.projeto.bar.projeto_bar.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> searchAll() {
        Sort sort = Sort.by("nome").ascending();
        return clienteRepository.findAll(sort);
    }

    public Cliente searchById(String id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada!"));
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        return clienteRepository.save(obj);
    }

    public Cliente update(Cliente obj) {
        return clienteRepository.save(obj);
    }
    
    public void deleteById(String id) {
        clienteRepository.deleteById(id);;
    }
    
}
