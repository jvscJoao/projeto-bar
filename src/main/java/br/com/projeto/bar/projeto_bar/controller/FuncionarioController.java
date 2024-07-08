package br.com.projeto.bar.projeto_bar.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.bar.projeto_bar.entity.Funcionario;
import br.com.projeto.bar.projeto_bar.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;
    
    FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    ResponseEntity<List<Funcionario>> searchAll() {
        List<Funcionario> list = funcionarioService.searchAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    ResponseEntity<Funcionario> searchById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(funcionarioService.searchById(id));
    }

    @PostMapping
    ResponseEntity<Funcionario> insert(@RequestBody Funcionario funcionario) {
        Funcionario obj = funcionarioService.insert(funcionario);
        URI locator = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(obj
            .getId())
        .toUri();
        return ResponseEntity.created(locator).build();
    }

    @PutMapping
    ResponseEntity<Funcionario> update(@RequestBody Funcionario funcionario) {
        Funcionario obj = funcionarioService.update(funcionario);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
